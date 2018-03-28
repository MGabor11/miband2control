package com.apppoweron.miband2control.ui.common

import android.arch.lifecycle.ViewModel
import com.apppoweron.miband2control.ui.navigationcontroller.NavigationController


abstract class BaseViewModelImpl : ViewModel(), BaseViewModel {

    override var navigationController: NavigationController? = null

}
