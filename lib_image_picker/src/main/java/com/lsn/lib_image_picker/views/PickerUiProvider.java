package com.lsn.lib_image_picker.views;

import android.content.Context;

import com.lsn.lib_image_picker.views.base.PickerControllerView;
import com.lsn.lib_image_picker.views.base.PickerFolderItemView;
import com.lsn.lib_image_picker.views.base.PickerItemView;
import com.lsn.lib_image_picker.views.base.PreviewControllerView;
import com.lsn.lib_image_picker.views.base.SingleCropControllerView;
import com.lsn.lib_image_picker.views.redbook.RedBookTitleBar;
import com.lsn.lib_image_picker.views.wx.WXBottomBar;
import com.lsn.lib_image_picker.views.wx.WXFolderItemView;
import com.lsn.lib_image_picker.views.wx.WXItemView;
import com.lsn.lib_image_picker.views.wx.WXPreviewControllerView;
import com.lsn.lib_image_picker.views.wx.WXSingleCropControllerView;
import com.lsn.lib_image_picker.views.wx.WXTitleBar;


/**
 * Time: 2019/10/27 22:22
 * Author:ypx
 * Description: 选择器UI提供类,默认为微信样式
 */
public class PickerUiProvider {

    /**
     * 获取标题栏
     *
     * @param context 调用此view的activity
     * @return {@link PickerControllerView}对象，参考{@link WXTitleBar} {@link RedBookTitleBar}
     *
     */
    public PickerControllerView getTitleBar(Context context) {
        return new WXTitleBar(context);
    }

    /**
     * 获取底部栏
     *
     * @param context 调用此view的activity
     * @return {@link PickerControllerView}对象，参考{@link WXBottomBar}
     *
     */
    public PickerControllerView getBottomBar(Context context) {
        return new WXBottomBar(context);
    }

    /**
     * 获取自定义item
     *
     * @param context 调用此view的activity
     *  @return {@link PickerItemView}对象，参考{@link WXBottomBar}
     *
     */
    public PickerItemView getItemView(Context context) {
        return new WXItemView(context);
    }

    /**
     * 获取自定义文件夹item
     *
     * @param context 调用此view的activity
     * @return {@link PickerFolderItemView}对象，参考{@link WXFolderItemView}
     *
     */
    public PickerFolderItemView getFolderItemView(Context context) {
        return new WXFolderItemView(context);
    }

    /**
     * 获取自定义预览界面
     *
     * @param context 调用此view的activity
     * @return {@link PreviewControllerView}对象，参考{@link WXPreviewControllerView}
     *
     */
    public PreviewControllerView getPreviewControllerView(Context context) {
        return new WXPreviewControllerView(context);
    }

    /**
     * 获取自定义单图剪裁界面
     *
     * @param context 调用此view的activity
     * @return {@link SingleCropControllerView}对象，参考{@link WXSingleCropControllerView}
     *
     */
    public SingleCropControllerView getSingleCropControllerView(Context context) {
        return new WXSingleCropControllerView(context);
    }
}
