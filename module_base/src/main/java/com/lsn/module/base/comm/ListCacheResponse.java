package com.lsn.module.base.comm;


import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2018/12/3
 * Description
 */
public interface ListCacheResponse<CACHE, RESULT, EXCEPTION> {

    void onEmptyStatusResponse();

    void onRequesting(Disposable disposable, List<CACHE> cache);

    void onSuccess(String key, @NonNull RESULT result);

    void onFailed(EXCEPTION exception);
}
