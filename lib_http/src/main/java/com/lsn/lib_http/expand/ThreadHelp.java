//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.6.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//

package com.lsn.lib_http.expand;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ThreadHelp {

    public static <T> ObservableTransformer<T, T> toMain() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}