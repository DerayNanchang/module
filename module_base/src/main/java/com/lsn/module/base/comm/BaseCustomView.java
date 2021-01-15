package com.lsn.module.base.comm;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.lsn.module.base.annotation.AntLayoutResId;


/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public abstract class BaseCustomView extends RelativeLayout {

    protected View itemView;

    public BaseCustomView(Context context) {
        this(context, null);
    }

    public BaseCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        if (getClass().getAnnotation(AntLayoutResId.class) != null) {
            itemView = View.inflate(getContext(), getClass().getAnnotation(AntLayoutResId.class).value(), this);
        }
        init();
    }

    protected abstract void init();

}
