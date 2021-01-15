package com.lsn.module.base.comm;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public class InconstantView extends RelativeLayout {

    private static String CONTENT = "CONTENT";
    private static String NO_CONNECT = "NO_CONNECT";
    private static String EMPTY_STATE = "EMPTY_STATE";
    private static String LOADING = "LOADING";
    private Type mType = Type.LOADING;

    public enum Type {
        CONTENT, NO_CONNECT, EMPTY_STATE, LOADING
    }

    public Type getType() {
        return mType;
    }

    public InconstantView(Context context) {
        this(context, null);
    }

    public InconstantView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InconstantView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void addContent(int resID) {
        addBaseView(resID, CONTENT);
    }


    public View getContent() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getTag() == CONTENT) {
                return getChildAt(i);
            }
        }
        return null;
    }

    public void addNoConnect(int resID) {
        addBaseView(resID, NO_CONNECT);
    }


    public void addEmptyState(int resID) {
        addBaseView(resID, EMPTY_STATE);
    }

    public void addLoading(int resID) {
        addBaseView(resID, LOADING);
    }


    private void addBaseView(int resID, String content) {
        View contentView = LayoutInflater.from(getContext()).inflate(resID, null, false);
        contentView.setTag(content);
        contentView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        params.addRule(CENTER_IN_PARENT);
        addView(
                contentView,
                params
        );
    }

    public void setBodyTransform(Type type) {
        switch (type) {
            case CONTENT: {
                bodyTransformEntity(CONTENT);
                mType = type;
            }
            break;
            case EMPTY_STATE: {
                bodyTransformEntity(EMPTY_STATE);
                mType = type;
            }
            break;
            case NO_CONNECT: {
                bodyTransformEntity(NO_CONNECT);
                mType = type;
            }
            break;
            case LOADING: {
                bodyTransformEntity(LOADING);
                mType = type;
            }
            break;
        }
    }

    private void bodyTransformEntity(String tag) {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getTag() != tag) {
                child.setVisibility(View.GONE);
            } else {
                child.setVisibility(View.VISIBLE);
            }
        }
    }
}
