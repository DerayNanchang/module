package com.lsn.lib_http.expand;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/4/22 15:58
 * Description
 */
public class XObserverAdapter<CACHE, ENTITY> extends XObserver<CACHE,ENTITY> {

    public XObserverAdapter(String key) {
        super(key);
    }

    @Override
    protected void onEmptyStatusResponse() {

    }

    @Override
    protected void onRequesting(Disposable d, @NotNull List<CACHE> cache) {

    }

    @Override
    protected void onRequesting(Disposable d, @NotNull CACHE cache) {

    }

    @Override
    protected void onSuccess(@NotNull String key, @NotNull ENTITY entity) {

    }

    @Override
    protected void onFailed(@NotNull Throwable e) {

    }
}
