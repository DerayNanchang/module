package com.lsn.lib_share.app;

import android.app.Application;

import com.lsn.lib_share.core.ShareInit;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/15
 * Description
 */
public class ShareApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 友盟相关初始化
        ShareInit.init(this);
    }
}
