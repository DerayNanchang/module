package com.lsn.lib_http.interceptor;

import android.text.TextUtils;
import com.lsn.lib_http.manager.ActNetManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/7/15
 * Description 是否启动请求
 */
public class IsNetInterceptor implements Interceptor {


    private final String actName;

    public IsNetInterceptor(String actName) {
        this.actName = actName;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!TextUtils.isEmpty(actName)) {
            ConcurrentHashMap<String, Boolean> actLiveMap = ActNetManager.get().getActLiveMap();
            if (actLiveMap != null) {
                Boolean actLive = actLiveMap.get(actName);
                if (actLive == null || !actLive) {
                    // 取消请求
                    chain.call().cancel();
                }
            }
        }
        Request newRequest = request.newBuilder().build();
        return chain.proceed(newRequest);
    }

}
