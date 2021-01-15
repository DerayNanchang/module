package com.lsn.module.base.comm;

import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.annotations.NonNull;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2019/1/22
 * Description
 */
public abstract class BaseViewHolder<DATA, VIEW extends BaseItemView<DATA>> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull VIEW itemView) {
        super(itemView);
    }
}
