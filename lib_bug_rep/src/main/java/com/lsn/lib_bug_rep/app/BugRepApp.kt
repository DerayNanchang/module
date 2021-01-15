package com.lsn.lib_bug_rep.app

import android.app.Application
import com.lsn.lib_bug_rep.BuildConfig
import com.tencent.bugly.Bugly

/**
 *
 *  Author: lsn
 *  Blog: https://www.jianshu.com/u/a3534a2292e8
 *  Date: 2021/1/13
 *  Description
 *
 */
abstract class BugRepApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // 这里选择用 bugly 作为bug上报并且集成了下载更新(要上架google商店则不能集成下载sdk)
        Bugly.init(applicationContext, BuildConfig.BUGLY_KEY, !BuildConfig.DEBUG)
    }
}