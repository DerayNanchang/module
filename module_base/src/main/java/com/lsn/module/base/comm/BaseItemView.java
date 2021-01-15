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
public abstract class BaseItemView<DATA> extends RelativeLayout {

    protected View itemView;

    public BaseItemView(Context context) {
        this(context, null);
    }

    public BaseItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (getClass().getAnnotation(AntLayoutResId.class) != null) {
            if (getResId() == 0) {
                itemView = View.inflate(getContext(), getClass().getAnnotation(AntLayoutResId.class).value(), this);
            } else {
                itemView = View.inflate(getContext(), getResId(), this);
            }
        }else {
            itemView = View.inflate(getContext(), getResId(), this);
        }
    }

    protected int getResId() {
        return 0;
    }


    protected abstract void bindData(DATA data, int position);
}
