package com.lsn.module.base.base.mvvm

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.lsn.module.base.base.comm.BaseActivity

/**
 *
 *  Author: lsn
 *  Blog: https://www.jianshu.com/u/a3534a2292e8
 *  Date: 2021/1/11
 *  Description MVVM 设计模式的基类
 *
 *  android AndroidViewModel(需要使用context),不然则使用 ViewModel即可
 *
 */
class BaseMVVMActivity<VM : ViewModel, VDB : ViewDataBinding> : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DataBindingUtil.inflate(LayoutInflater.from(this), R.layou)
    }
}