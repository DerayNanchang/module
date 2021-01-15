package com.lsn.lib_dialog.blurred;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/12
 * Description
 */
public class DefaultSnapshotInterceptor implements Blurred.SnapshotInterceptor {
    @Override
    public Bitmap snapshot(View from, int backgroundColor, int foregroundColor, float scale, boolean antiAlias) {
        return BitmapProcessor.get().snapshot(from, backgroundColor, foregroundColor, scale, antiAlias);
    }
}
