package com.lsn.lib_http.manager;


import com.lsn.lib_http.config.BaseUrl;
import com.lsn.lib_http.config.FastJsonConverterFactory;
import com.lsn.lib_http.HttpConstant;
import com.lsn.lib_http.config.TrustAllCerts;
import com.lsn.lib_http.interceptor.DefaultHeardInterceptor;
import com.lsn.lib_http.interceptor.IsNetInterceptor;
import com.lsn.lib_http.interceptor.ResponseInterceptor;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by Chris on 2018/4/18.
 */

public class HttpManager {

    private String retrofitUrl = "";
    private String actName;
    private boolean isConnAct = HttpConstant.IS_CONN_ACT;


    @NotNull
    public HttpManager setActName(String actName) {
        this.actName = actName;
        return this;
    }


    public HttpManager isNet(boolean isNet) {
        this.isConnAct = isNet;
        return this;
    }


    public <T> T get(Class<T> clazz) {
        return get(clazz, false);
    }

    public <T> T get(Class<T> clazz, boolean isToken) {
        return get(clazz, BaseUrl.RELEASE, isToken);
    }


    public <T> T get(Class<T> clazz, String tag) {
        return get(clazz, tag, false);
    }


    public <T> T get(Class<T> clazz, String tag, boolean isAddToken) {
        retrofitUrl = tag;
        if (isAddToken) {
            return getBaseManager(retrofitUrl, true).create(clazz);
        } else {
            return getBaseManager(retrofitUrl, false).create(clazz);
        }
    }


    public <T> T post(Class<T> clazz) {
        return post(clazz, false);
    }

    public <T> T post(Class<T> clazz, boolean isToken) {
        return post(clazz, BaseUrl.RELEASE, isToken);
    }

    public <T> T post(Class<T> clazz, String tag) {
        return post(clazz, tag, false);
    }


    public <T> T post(Class<T> clazz, String tag, boolean isAddToken) {
        retrofitUrl = tag;
        if (isAddToken) {
            return getBaseManager(retrofitUrl, true).create(clazz);
        } else {
            return getBaseManager(retrofitUrl, false).create(clazz);
        }
    }


    public Retrofit getBaseManager(String baseUrl, boolean isToken) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(HttpConstant.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(HttpConstant.READ_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(HttpConstant.WRITE_TIMEOUT, TimeUnit.SECONDS);
        // 默认添加是否取消请求
        if (isConnAct) {
            builder.addInterceptor(new IsNetInterceptor(actName));
        }
        if (isToken) {
            builder.addInterceptor(new DefaultHeardInterceptor());     // 添加 token
            builder.addInterceptor(new ResponseInterceptor());          // 检测 token 失效
        }
        if (HttpConstant.IS_SSL) {
            builder.sslSocketFactory(TrustAllCerts.createSSLSocketFactory());
            builder.hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier());
        }
        builder.protocols(Collections.singletonList(Protocol.HTTP_1_1));
        OkHttpClient build = builder.build();
        return getBaseManager(baseUrl, build);
    }


    /**
     * 默认添加 token
     *
     * @param baseUrl
     * @return
     */
    private Retrofit getBaseManager(String baseUrl) {
        return getBaseManager(baseUrl, true);
    }


    private Retrofit getBaseManager(String baseUrl, OkHttpClient client) {
        isConnAct = true;
        return setBaseUrl(baseUrl)
                .client(client)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    private Retrofit.Builder getBaseRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    private Retrofit.Builder setBaseUrl(String baseUrl) {
        return getBaseRetrofitBuilder().baseUrl(baseUrl);
    }

    private HttpManager() {

    }


    private static class HttpManagerInstance {
        private static final HttpManager manager = new HttpManager();
    }

    public static HttpManager request() {
        return HttpManagerInstance.manager;
    }
}
