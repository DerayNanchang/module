package com.lsn.lib_image_picker.data;

import androidx.annotation.Nullable;

import com.lsn.lib_image_picker.bean.ImageItem;


public interface ICameraExecutor {

    void takePhoto();

    void takeVideo();

    void onTakePhotoResult(@Nullable ImageItem imageItem);
}
