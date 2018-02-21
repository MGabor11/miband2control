package com.apppoweron.miband2control.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), FragmentCommunicator {

    private var mBackPressListener: BackPressListener? = null

    open val containerId: Int
        get() = 0

    @Throws(NoContainerException::class)
    private fun addFragment(fragment: Fragment) {
        if (!hasContainerId()) {
            throw NoContainerException()
        }
        supportFragmentManager.beginTransaction().add(containerId, fragment, fragment.javaClass.simpleName + "_TAG").commit()
    }

    @Throws(NoContainerException::class)
    private fun replaceFragment(fragment: Fragment, needToBackStack: Boolean) {
        if (!hasContainerId()) {
            throw NoContainerException()
        }
        val transaction = supportFragmentManager.beginTransaction().replace(containerId, fragment, fragment.javaClass.simpleName + "_TAG")
        if (needToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }


    @Throws(NoContainerException::class)
    @JvmOverloads
    fun loadFragment(fragment: Fragment, loadType: FragmentLoadType, needToBackStack: Boolean = false) {
        when (loadType) {
            BaseActivity.FragmentLoadType.ADD -> addFragment(fragment)
            BaseActivity.FragmentLoadType.REPLACE -> replaceFragment(fragment, needToBackStack)
        }
    }

    private fun hasContainerId(): Boolean {
        return containerId != DEFAULT_CONTAINER_ID
    }

    override fun onBackPressed() {
        if (mBackPressListener != null) {
            mBackPressListener!!.onBackPressed()
        } else {
            callSuperBackPressed()
        }
    }

    internal fun setBackPressListener(listener: BackPressListener) {
        mBackPressListener = listener
    }

    private fun callSuperBackPressed() {
        super.onBackPressed()
    }

    internal interface BackPressListener {
        fun onBackPressed()
    }

    enum class FragmentLoadType {
        ADD, REPLACE
    }


    /**
     * Remove items from backstack
     */
    protected fun clearBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    @Throws(NoContainerException::class)
    override fun onNewFragmentSelected(fragment: Fragment, replaceIt: Boolean, needToBackStack: Boolean) {
        if (replaceIt) {
            loadFragment(fragment, FragmentLoadType.REPLACE, needToBackStack)
        } else {
            loadFragment(fragment, FragmentLoadType.ADD, needToBackStack)
        }

    }

    companion object {

        private val DEFAULT_CONTAINER_ID = -1
    }


}
