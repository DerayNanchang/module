package com.lsn.module.base.wedgit.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.lsn.lib_util.util.ConvertUtils;
import com.lsn.lib_util.util.ScreenUtils;

import java.util.Objects;

import io.reactivex.annotations.NonNull;


/**
 * Created by luocheng on 18/4/13.
 */

public abstract class BaseDialog extends DialogFragment {

    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM_AMOUNT = "dim_amount";
    private static final String GRAVITY = "gravity";
    private static final String OUT_CANCEL = "out_cancel";
    private static final String ANIM_STYLE = "anim_style";
    private static final String IS_NO_TITLE = "is_no_title";

    private boolean mIsNoTitle = true;
    private int mGravity = Gravity.CENTER;
    private int mWidth;
    private int mHeight;
    private int mAnimationsStyle;
    private int mMargin = 20;
    private boolean mIsCanceledOnTouchOutside = false;
    private float mDimAmount = 0.5f;



    public abstract int getDialogResId();

    public abstract void initView(View view);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getDialogResId(), container, false);
        if (mIsNoTitle) {
            Objects.requireNonNull(getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
        }

        initView(view);
        return view;
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogParams();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 恢复保存的数据
        if (savedInstanceState != null) {
            mIsNoTitle = savedInstanceState.getBoolean(IS_NO_TITLE);
            mMargin = savedInstanceState.getInt(MARGIN);
            mWidth = savedInstanceState.getInt(WIDTH);
            mHeight = savedInstanceState.getInt(HEIGHT);
            mDimAmount = savedInstanceState.getFloat(DIM_AMOUNT);
            mGravity = savedInstanceState.getInt(GRAVITY);
            mIsCanceledOnTouchOutside = savedInstanceState.getBoolean(OUT_CANCEL);
            mAnimationsStyle = savedInstanceState.getInt(ANIM_STYLE);
        }
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_NO_TITLE, mIsNoTitle);
        outState.putInt(MARGIN, mMargin);
        outState.putInt(WIDTH, mWidth);
        outState.putInt(HEIGHT, mHeight);
        outState.putFloat(DIM_AMOUNT, mDimAmount);
        outState.putInt(GRAVITY, mGravity);
        outState.putBoolean(OUT_CANCEL, mIsCanceledOnTouchOutside);
        outState.putInt(ANIM_STYLE, mAnimationsStyle);
    }

    private void setDialogParams() {
        Window window = getDialog().getWindow();
        getDialog().setCanceledOnTouchOutside(mIsCanceledOnTouchOutside);
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            // 调节灰色背景透明度[0-1]，默认0.5f
            params.dimAmount = mDimAmount;
            params.gravity = mGravity;
            if (mWidth == -1) {
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
            } else if (mWidth == -2) {
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
            } else if (mWidth == 0) {
                params.width = ScreenUtils.getScreenWidth()
                        - 2 * ConvertUtils.dp2px(mMargin);
            } else {
                params.width = ConvertUtils.dp2px(mWidth);
            }
            if (mHeight == 0) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                params.height = ConvertUtils.dp2px(mHeight);
            }
            window.setWindowAnimations(mAnimationsStyle);
            window.setAttributes(params);
            // 设置该dialogFragment的背景为透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setAttributes(params);
        }
    }

    public BaseDialog setGravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    public BaseDialog setNoTitle(boolean isNoTitle) {
        mIsNoTitle = isNoTitle;
        return this;
    }

    public BaseDialog setWidth(int width) {
        if (width != -1 && width != -2 && width <= 0) {
            return this;
        }
        mWidth = width;
        return this;
    }

    public BaseDialog setHight(int height) {
        if (height != -1 && height != -2 && height <= 0) {
            return this;
        }
        mHeight = height;
        return this;
    }

    public BaseDialog setMargin(int margin) {
        if (margin <= 0) {
            return this;
        }
        mMargin = margin;
        return this;
    }

    public BaseDialog setAnimations(int animationsStyle) {
        mAnimationsStyle = animationsStyle;
        return this;
    }

    public BaseDialog setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
        mIsCanceledOnTouchOutside = isCanceledOnTouchOutside;
        return this;
    }

    public BaseDialog setDimAmount(float dimAmount) {
        if (dimAmount < 0 || dimAmount > 1) {
            return this;
        }
        mDimAmount = dimAmount;
        return this;
    }

}
