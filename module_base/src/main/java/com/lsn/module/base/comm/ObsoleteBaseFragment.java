package com.lsn.module.base.comm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lsn.module.base.annotation.AntLayoutResId;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2018/12/3
 * Description
 */
abstract public class ObsoleteBaseFragment extends IBaseRefreshFragment implements IBaseView {

    private boolean isFmtVisible = false;     // fragment 是否可视（by 懒加载）
    private boolean isPrepared = false;       // 是否准备好了  (by 懒加载1)
    private boolean isFirst = true;
    protected View view;

    private CompositeDisposable disposableList;

    @NotNull
    @Override
    public String msg(int msg) {
        return "";
    }

    public ObsoleteBaseFragment() {
    }

    protected void addDisposable(Disposable disposable) {
        if (disposable != null) {
            disposableList.add(disposable);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void lazyLoadValid() {
        if (isFmtVisible && isPrepared) {
            baseInit();
        }
        /*if (isFmtVisible && isPrepared && isFirst) {
            baseInit();
            isFirst = false;
        }*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AntLayoutResId annotation = getClass().getAnnotation(AntLayoutResId.class);
        disposableList = new CompositeDisposable();
        //createDialog();
        if (getResView() == null && annotation != null) {
            view = LayoutInflater.from(getContext()).inflate(getClass().getAnnotation(AntLayoutResId.class).value(), null, false);
            return view;
        } else if (getResView() != null && annotation != null) {
            RelativeLayout rvRoot = new RelativeLayout(getContext());
            view = LayoutInflater.from(getContext()).inflate(getClass().getAnnotation(AntLayoutResId.class).value(), null, false);
            // 添加基础页面
            rvRoot.addView(getResView());
            // 添加子Fragment
            rvRoot.addView(view);
            return rvRoot;
        } else {
            return super.onCreateView(inflater,container,savedInstanceState);
        }
    }

    protected View getResView() {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;   // 会先执行 setUserVisibleHint 方法再执行 onActivityCreated 为了避免空指针需要做个标记
        if (isLazyLoad()) {
            lazyLoadValid();
        } else {
            // 不使用懒加载
            baseInit();
        }
    }


    /**
     * 是否懒加载
     *
     * @return 默认不懒加载
     */
    protected boolean isLazyLoad() {
        return false;
    }


    protected abstract void baseInit();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isLazyLoad()) {
            isFmtVisible = isVisibleToUser;
            if (getUserVisibleHint()) {
                isFmtVisible = true; // 可见
            } else {
                isFmtVisible = false; // 不可见
            }
            if (isVisibleToUser) {
                lazyLoadValid();
            }
        }
    }


    protected void startActivity(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isLazyLoad()) {
            isFmtVisible = false;
            isPrepared = false;
            isFirst = true;
        }
        if (disposableList != null) {
            disposableList.clear();
            disposableList = null;
        }
        dismissDialog();
    }
}
