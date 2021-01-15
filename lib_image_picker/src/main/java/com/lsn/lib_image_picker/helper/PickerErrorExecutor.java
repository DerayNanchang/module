package com.lsn.lib_image_picker.helper;

import android.app.Activity;

import com.lsn.lib_image_picker.bean.PickerError;
import com.lsn.lib_image_picker.data.OnImagePickCompleteListener;
import com.lsn.lib_image_picker.data.OnImagePickCompleteListener2;


/**
 * Time: 2019/10/18 9:53
 * Author:ypx
 * Description: 调用选择器失败回调
 */
public class PickerErrorExecutor {

    public static void executeError(Activity activity, int code) {
        activity.setResult(code);
        activity.finish();
    }

    public static void executeError(OnImagePickCompleteListener listener, int code) {
        if (listener instanceof OnImagePickCompleteListener2) {
            ((OnImagePickCompleteListener2) listener).onPickFailed(PickerError.valueOf(code));
        }
    }
}
