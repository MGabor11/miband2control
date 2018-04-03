package com.apppoweron.miband2control.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import com.apppoweron.miband2control.R
import com.apppoweron.miband2control.ui.common.BaseActivity
import com.apppoweron.miband2control.ui.navigationcontroller.NavigationController
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    companion object {
        const val TAG = "MainActivity"
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigationControllerImpl: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            loadFragment(MainActivityFragment.newInstance(),FragmentLoadType.ADD,false) //TODO with navigator
        }

    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override val containerId: Int
        get() = R.id.fragment_container
}
