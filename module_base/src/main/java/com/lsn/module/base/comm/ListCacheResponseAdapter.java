package com.lsn.module.base.comm;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public  class ListCacheResponseAdapter<CACHE, RESULT, EXCEPTION> implements ListCacheResponse<CACHE, RESULT, EXCEPTION> {

    @Override
    public void onEmptyStatusResponse() {

    }

    @Override
    public void onRequesting(Disposable disposable, List<CACHE> cache) {

    }

    @Override
    public void onSuccess(String key, RESULT result) {

    }

    @Override
    public void onFailed(EXCEPTION exception) {

    }
}
