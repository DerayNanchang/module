package com.lsn.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 *  Author: lsn
 *  Blog: https://www.jianshu.com/u/a3534a2292e8
 *  Date: 2021/1/12
 *  Description
 *
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init(savedInstanceState)

    }

    abstract fun getLayoutResId(): Int

    abstract fun init(savedInstanceState: Bundle?)
}