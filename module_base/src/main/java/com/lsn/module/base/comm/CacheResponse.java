package com.lsn.module.base.comm;


import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2018/12/3
 * Description
 */
public interface CacheResponse<CACHE, RESULT, EXCEPTION> {

    void onEmptyStatusResponse();

    void onRequesting(Disposable disposable, CACHE cache);

    void onSuccess(String key, @NonNull RESULT result);

    void onFailed(EXCEPTION exception);
}
