package com.lsn.lib_dialog.blurred;

import android.graphics.Bitmap;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/12
 * Description
 */
public interface IBlur {
    /**
     * 高斯模糊图片
     *
     * @param originalBitmap  原图
     * @param radius          模糊半径
     * @param scale           缩小因子
     * @param keepSize        缩小后是否再次放大为原图尺寸
     * @param recycleOriginal 回收原图
     * @return 模糊图
     */
    Bitmap process(final Bitmap originalBitmap,
                   final float radius,
                   final float scale,
                   final boolean keepSize,
                   final boolean recycleOriginal);

    /**
     * 回收资源
     */
    void recycle();
}
