package com.lsn.module.base.comm;


import com.lsn.lib_http.HttpConstant;
import com.lsn.module.base.utils.Toast;

import org.jetbrains.annotations.NotNull;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/7/2
 * Description
 */
public abstract class BaseNetFragment extends ObsoleteBaseFragment implements IBaseNetResp {
    @Override
    protected void baseInit() {
        if (getActivity() == null) {
            // 如果父亲本来为 null 就不做任何事情
            return;
        }
        if (!isLazyLoad()) {
            showDialog();
        }
        baseNetInit();
    }

    protected abstract void baseNetInit();


   /* @Override
    public void onEmptyStatusResponse() {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        dismissDialog();
        if (inconstantView != null) {
            inconstantView.setBodyTransform(InconstantView.Type.EMPTY_STATE);
        }
    }*/


    @Override
    public void onEmptyStatusResponse(@NotNull String tag, @NotNull String msg) {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        dismissDialog();
        if (inconstantView != null) {
            inconstantView.setBodyTransform(InconstantView.Type.EMPTY_STATE);
        }
    }

    @Override
    public void onSuccess(String tag, String mode, boolean isCache, Object entity) {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        dismissDialog();
    }

    @Override
    public void onFailed(int mode, String msg) {
        // 取消刷新
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        if (mode == HttpConstant.FAILED_CONNECT) {
            //Toast.show("电波无法到达服务器");
        } else {
            Toast.show(msg);
        }
        dismissDialog();
    }

}
