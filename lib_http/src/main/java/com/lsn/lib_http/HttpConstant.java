package com.lsn.lib_http;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/6
 * Description
 */
public interface HttpConstant {

    // 基础值后续业务逻辑继承基础值即可
    String DEBUG_URL = BuildConfig.API_SERVER_URL;
    String RELEASE_URL = DEBUG_URL;
    boolean IS_SSL = BuildConfig.IS_SSL;                            // 加载SSL
    boolean IS_CONN_ACT = BuildConfig.IS_CONN_ACT;                  // 是否与activity建立联系(当Activity 退出是就终止请求)
    int CONNECT_TIMEOUT = BuildConfig.CONNECT_TIMEOUT;              // 连接时间设置
    int READ_TIMEOUT = BuildConfig.READ_TIMEOUT;                    // 读取时间设置
    int WRITE_TIMEOUT = BuildConfig.WRITE_TIMEOUT;                  // 写入时间设置
    int FAILED_SERVICE = BuildConfig.FAILED_SERVICE;                // 请求成功，但是返回失败
    int FAILED_CONNECT = BuildConfig.FAILED_CONNECT;                // 请求失败

}
