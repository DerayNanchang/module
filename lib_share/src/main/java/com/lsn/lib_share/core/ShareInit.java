package com.lsn.lib_share.core;

import android.app.Application;

import com.lsn.lib_share.BuildConfig;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/15
 * Description
 */
public class ShareInit {
    public static void init(Application application) {
        /*UMConfigure.init(application, "UMAPPAppKey", channel, UMConfigure
                .DEVICE_TYPE_PHONE, "");
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL);
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin("wxe48cbe48ede0d8dc", "c7adce412dd4629ecc2817d5496bfb71");
        PlatformConfig.setQQZone("1108287325", "IgqLD01lBBz7AjzY");
        PlatformConfig.setSinaWeibo("3797284088", "66587ff8351984e229fffd72c1ed02c8", "");
        UMShareAPI umShareAPI = UMShareAPI.get(application);
        umShareAPI.setShareConfig(new UMShareConfig().isNeedAuthOnGetUserInfo(true));
        MobclickAgent.setCatchUncaughtExceptions(true);*/

        UMConfigure.init(application, BuildConfig.UM_APP_ID
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        // 微信设置 appId appSecret
        PlatformConfig.setWeixin(BuildConfig.WX_APP_ID, "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setWXFileProvider("com.tencent.sample2.fileprovider");
        // QQ设置 QQ和Qzone appid appkey appSecret
        PlatformConfig.setQQZone(BuildConfig.QQ_APP_ID, "5d63ae8858f1caab67715ccd6c18d7a5");
        PlatformConfig.setQQFileProvider("com.tencent.sample2.fileprovider");
        // 企业微信设置 appSecret
        PlatformConfig.setWXWork(BuildConfig.WX_WORK_APP_ID, "EU1LRsWC5uWn6KUuYOiWUpkoH45eOA0yH-ngL8579zs", "1000002", "wwauthac6ffb259ff6f66a000002");
        PlatformConfig.setWXWorkFileProvider("com.tencent.sample2.fileprovider");
        // 其他平台设置
        // 新浪微博 appkey appsecret appSecret
        PlatformConfig.setSinaWeibo(BuildConfig.SINA_WEIBO_APP_ID, "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        // 钉钉 appId
        PlatformConfig.setDing(BuildConfig.DING_APP_ID);
        //支付宝 appid
        PlatformConfig.setAlipay(BuildConfig.ALIPAY_APP_ID);
    }
}
