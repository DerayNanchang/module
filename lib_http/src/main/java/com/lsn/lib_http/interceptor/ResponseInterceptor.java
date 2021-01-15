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
public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        /*Request chainRequest = chain.request();
        // 在 请求失败的时候已经被 RxJava 拦截掉了，所以无法获取到 chainResponse 的响应值

        Response chainResponse = chain.proceed(chainRequest);
        ResponseBody body = chainResponse.body();
        if (body != null) {
            String bodyStr = body.string();
            if (!TextUtils.isEmpty(bodyStr)) {
                RespEntity respEntity = JSON.parseObject(bodyStr, RespEntity.class);
                if (respEntity.getCode() == 9001 || respEntity.getCode() == 1000) {
                    if (!NoDoubleClickUtil.isDoubleClick()){
                        UserCacheUtil.logOut(true);
                    }


                    //Toast.show("token 已经失效请重新登录");
                }
                *//*if (bodyStr.contains("9001") || bodyStr.contains("1000")) {
                    Toast.show("token 已经失效请重新登录");
                }*//*

                *//*val jsonObject = JSONObject(bodyStr)
                val status = jsonObject.getInt("status")
                if (status == Constant.NET.TOKEN_FAILED) {
                    // token 失效
                    EventManager.event.tokenFailedSend()
                }*//*
            }
            return chainResponse.newBuilder()
                    .body(ResponseBody.create(MediaType.parse("UTF-8"), bodyStr))
                    .build();
        }*/
        Request chainRequest1 = chain.request();
        return chain.proceed(chainRequest1);
    }
}
