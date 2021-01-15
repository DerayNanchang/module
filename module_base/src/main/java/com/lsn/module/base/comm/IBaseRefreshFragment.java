package com.lsn.module.base.comm;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lsn.module.base.R;


/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/7/2
 * Description
 */
public class IBaseRefreshFragment extends BaseDialogFragment implements IBaseRefreshEntity {
    public SwipeRefreshLayout refreshLayout;
    protected InconstantView inconstantView;

    @Override
    public void setBody(InconstantView inconstantView) {
        this.inconstantView = inconstantView;
        // 添加空状态与无网络
        if (inconstantView != null) {
            inconstantView.addContent(R.layout.view_default_content);
            inconstantView.addEmptyState(R.layout.view_default_empty_state);
            inconstantView.addNoConnect(R.layout.view_default_no_connect);
            inconstantView.addLoading(R.layout.view_comm_progress);
            inconstantView.setBodyTransform(InconstantView.Type.LOADING);
        }
    }

    @Override
    public void setRefreshView(SwipeRefreshLayout srlView) {
        if (srlView != null){
            refreshLayout = srlView;
            if (getContext() != null){
                refreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.theme_color));
            }
        }
    }
}
