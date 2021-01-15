package com.lsn.module.base.comm;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public abstract class CustomHeadOrFootView extends HeadOrFootItemView<String> {
    public CustomHeadOrFootView(Context context) {
        super(context);
    }

    public CustomHeadOrFootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHeadOrFootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
