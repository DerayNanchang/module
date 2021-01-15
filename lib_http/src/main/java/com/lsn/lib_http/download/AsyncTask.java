package com.lsn.lib_http.download;

import android.os.Looper;

import com.lsn.lib_http.queue.DispatchThread;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/14
 * Description
 */
public class AsyncTask {

    private static final DispatchThread MAIN_QUEUE = new DispatchThread(Looper.getMainLooper());

    protected void publishProgress(final Integer... values) {
        MAIN_QUEUE.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(values);
            }
        });
    }


    protected void onProgressUpdate(Integer... values) {

    }
}
