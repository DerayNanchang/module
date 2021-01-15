package com.lsn.lib_http.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/2
 * Description
 */
public class DefaultHeardInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        /*UserInfo userInfo = UserCacheUtil.getUserInfo();
        String token = UserCacheUtil.getToken();
        if (userInfo == null) {
            Toast.show("请重新登录");
            *//*if (UserCacheUtil.checkIsLoginWithJump(null)) {
                Navigation.navigateToRedPublish(null);
            }*//*
        }
        XYZ xyz = CBApp.getContext().getXYZ();
        if (xyz == null) {
            xyz = new XYZ();
            xyz.setLatitude(Double.parseDouble(Constant.Conn.TEST_LAT));
            xyz.setLongitude(Double.parseDouble(Constant.Conn.TEST_LNG));
        }

        Request chainRequest = chain.request();
        Request.Builder newBuilder = chainRequest.newBuilder();
        // 添加请求拦截
        if (userInfo != null) {
            newBuilder.addHeader(Constant.Net.Heard.UUID, userInfo.getUuid());
        }
        newBuilder.addHeader(Constant.Net.Heard.LNG, xyz.getLongitude() + "");
        newBuilder.addHeader(Constant.Net.Heard.LAT, xyz.getLatitude() + "");
        newBuilder.addHeader(Constant.Net.Heard.ACCESS_TOKEN, token);
        newBuilder.addHeader(Constant.Net.Heard.DEVICE_ID, Constant.Conn.Android);
        Request build = newBuilder.build();
        return chain.proceed(build);*/
        /*Request chainRequest = chain.request();
        Request.Builder newBuilder = chainRequest.newBuilder();
        Request build = newBuilder.build();
        return chain.proceed(build);*/
        Request chainRequest1 = chain.request();
        return chain.proceed(chainRequest1);
    }

}
