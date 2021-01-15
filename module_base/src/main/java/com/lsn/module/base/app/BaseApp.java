package com.lsn.module.base.app;

import android.app.Application;

import com.lsn.module.base.utils.NetUtil;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/12/30
 * Description
 */
public abstract class BaseApp extends Application {

    private static BaseApp context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static BaseApp getContext() {
        return context;
    }

    public Boolean isNetConnect() {
        return NetUtil.isNetworkConnected(context);
    }
}
