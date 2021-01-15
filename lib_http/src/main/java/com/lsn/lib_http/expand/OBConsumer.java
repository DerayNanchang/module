package com.lsn.lib_http.expand;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Chris on 2018/4/18.
 */

public abstract class OBConsumer<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
        //requesting(d);
    }

    @Override
    public void onNext(T o) {
        complete(o);
    }


    @Override
    public void onComplete() {
    }


    @Override
    public void onError(Throwable e) {
        error(e);
    }

    // 加载中
    //protected abstract void requesting(Disposable d);

    // 加载成功
    protected abstract void complete(T o);

    // 加载失败
    protected abstract void error(Throwable e);


}
