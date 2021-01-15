package com.lsn.module.base.base.comm

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lsn.module.base.R
import com.lsn.module.base.annotation.YaoYaoAnnotation
import com.lsn.module.base.comm.IBaseNetResp
import com.lsn.module.base.comm.InconstantView

/**
 *
 *  Author: lsn
 *  Blog: https://www.jianshu.com/u/a3534a2292e8
 *  Date: 2021/1/11
 *  Description
 *
 */
abstract class BaseActivity : AppCompatActivity(), IBaseNetResp {


    private val statusBarView: View? = null
    private val refreshLayout: SwipeRefreshLayout? = null
    private var alertDialog: AlertDialog? = null
    private var baseDialog: View? = null
    //private var disposableList: CompositeDisposable? = null
    private val inconstantView: InconstantView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Android 5.0 转场动画
        //androidLAnimation()
        super.onCreate(savedInstanceState)
        //disposableList = CompositeDisposable()
        // 注解相关
        annotationSettings()
        // dialog
        initBaseDialog()

    }

    private fun annotationSettings() {
        // 初始化网络监听
        YaoYaoAnnotation.get().initAnnotation(this)
    }


    /*protected fun addDisposable(disposable: Disposable?) {
        if (disposable != null) {
            disposableList?.add(disposable)
        }
    }*/

    private fun initBaseDialog() {
        baseDialog = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null, false)
        alertDialog = AlertDialog.Builder(this).setView(baseDialog).create()
    }

    protected fun showDialog() {
        showDialog("")
    }

    protected fun showDialog(msg: String) {
        if (baseDialog == null) {
            baseDialog = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null, false)
        }
        if (alertDialog == null) {
            alertDialog = AlertDialog.Builder(this).setView(baseDialog)
                .create()
        }
        alertDialog?.show()
        updateWidth()
    }

    private fun updateWidth() {
        val lp = alertDialog?.window?.attributes
        lp?.apply {
            width = resources.getDimensionPixelSize(R.dimen.dp_200)
            height = resources.getDimensionPixelSize(R.dimen.dp_200)
            alertDialog?.window?.attributes = lp
        }
    }


    protected fun dismissDialog() {
        if (alertDialog != null) {
            if (baseDialog?.parent != null) {
                if (baseDialog?.parent is ViewGroup) {
                    (baseDialog?.parent as ViewGroup).removeView(baseDialog)
                }
            }
            alertDialog?.dismiss()
            alertDialog = null
        }
    }

    override fun onEmptyStatusResponse(tag: String, msg: String) {
        if (refreshLayout != null) {
            refreshLayout.isRefreshing = false
        }
        dismissDialog()
        inconstantView?.setBodyTransform(InconstantView.Type.EMPTY_STATE)
    }

    override fun onSuccess(tag: String, mode: String, isCache: Boolean, entity: Any) {
        if (refreshLayout != null) {
            refreshLayout.isRefreshing = false
        }
        dismissDialog()
        inconstantView?.setBodyTransform(InconstantView.Type.CONTENT)
    }

    override fun onFailed(mode: Int, msg: String) {
        // 取消刷新
        /*dismissDialog()
        if (refreshLayout != null) {
            refreshLayout.isRefreshing = false
        }
        if (mode == HttpConstant.FAILED_CONNECT) {
            //Toast.show("电波无法到达服务器");
        } else {
            // 弹出失败的相关文本信息
            runOnUiThread { Toast.show(msg) }
        }*/
    }
}