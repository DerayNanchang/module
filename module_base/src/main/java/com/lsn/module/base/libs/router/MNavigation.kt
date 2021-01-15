package com.lsn.module.base.libs.router

import android.net.Uri
import android.text.TextUtils
import androidx.core.app.ActivityOptionsCompat
import com.alibaba.android.arouter.launcher.ARouter

/**
 *
 *  Author: lsener
 *  Date: 2020/12/30
 *  Description
 *
 */
object MNavigation {








    private fun urlNotNull(url: String?) {
        if (TextUtils.isEmpty(url)) {
            return
        }
    }

    fun toWebComm(url: String?, title: String?) {
        urlNotNull(url)
        /*ARouter.getInstance().build(RouterPaths.ACT_WEB_COMM)
            .withOptionsCompat(null)
            .withString(Constants.Comm.KEY_STR_ID, url)
            .withString(Constants.Comm.KEY_STR_ID, title)
            .navigation()*/
    }

    fun toWebComm(url: String, title: String?, activityOptionsCompat: ActivityOptionsCompat?) {
        urlNotNull(url)
        /*ARouter.getInstance().build(RouterPaths.ACT_WEB_COMM)
            .withOptionsCompat(activityOptionsCompat)
            .withString(Constants.Comm.KEY_STR_ID, url)
            .withString(Constants.Comm.KEY_STR_ID, title)
            .navigation()*/
    }


    fun urlNavigation(url: String, title: String?) {
        urlNotNull(url)
        try {
            ARouter.getInstance().build(Uri.parse(url))
                .withOptionsCompat(null)
                .navigation()
        } catch (e: Exception) {
            toWebComm(url, title, null)
        }
    }

    fun urlNavigation(url: String, title: String?, activityOptionsCompat: ActivityOptionsCompat?) {
        urlNotNull(url)
        try {
            ARouter.getInstance().build(Uri.parse(url))
                .withOptionsCompat(activityOptionsCompat)
                .navigation()
        } catch (e: Exception) {
            toWebComm(url, title, activityOptionsCompat)
        }
    }

}