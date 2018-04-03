package com.apppoweron.miband2control.ui


import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apppoweron.miband2control.R
import com.apppoweron.miband2control.binding.FragmentDataBindingComponent
import com.apppoweron.miband2control.databinding.FragmentMainBinding
import com.apppoweron.miband2control.di.Injectable
import com.apppoweron.miband2control.ui.common.BaseFragment
import com.apppoweron.miband2control.ui.navigationcontroller.NavigationController
import com.apppoweron.miband2control.util.AutoClearedValue
import javax.inject.Inject

class MainActivityFragment : BaseFragment(), Injectable {

    companion object {
        fun newInstance(): MainActivityFragment {
            return MainActivityFragment()
        }
    }

    @Inject
    lateinit var navigationControllerImpl: NavigationController

    var binding: AutoClearedValue<FragmentMainBinding>? = null

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val dataBinding : FragmentMainBinding= DataBindingUtil
                .inflate<ViewDataBinding>(inflater, R.layout.fragment_main, container, false, dataBindingComponent) as FragmentMainBinding
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }


}
