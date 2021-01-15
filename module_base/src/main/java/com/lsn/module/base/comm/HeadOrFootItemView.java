package com.lsn.module.base.comm;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public abstract class HeadOrFootItemView<DATA> extends BaseItemView<DATA> {
    public HeadOrFootItemView(Context context) {
        super(context);
    }

    public HeadOrFootItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadOrFootItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setMsg(int msg);

    @Override
    protected void bindData(DATA data, int position) {

    }
}
