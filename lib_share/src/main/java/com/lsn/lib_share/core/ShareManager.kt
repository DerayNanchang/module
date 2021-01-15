package com.lsn.lib_share.core

import android.app.Activity
import com.lsn.lib_share.bean.ShareMQQBean
import com.lsn.lib_share.bean.ShareMWXBean
import com.lsn.lib_share.bean.ShareUrlBean
import com.lsn.lib_share.bean.ShareVideoBean
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.*
import java.io.File

/**
 *
 *  Author: lsn
 *  Blog: https://www.jianshu.com/u/a3534a2292e8
 *  Date: 2021/1/14
 *  Description
 *
 */
class ShareManager private constructor() {

    companion object {
        var sm = ShareManager()
    }

    private class MUMShareListener : UMShareListener {
        override fun onStart(platform: SHARE_MEDIA?) {
            // 开始分享
        }

        override fun onResult(platform: SHARE_MEDIA?) {
            // 分享成功
        }

        override fun onCancel(platform: SHARE_MEDIA?) {
            // 分享取消
        }

        override fun onError(platform: SHARE_MEDIA?, t: Throwable?) {
            // 分享失败
        }
    }

    /**
     *  分享文本
     */
    fun shareText(activity: Activity, share_media: SHARE_MEDIA, text: String) {
        ShareAction(activity).withText(text)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }

    /**
     *  分享本地图片
     */
    fun shareImageLocal(activity: Activity, share_media: SHARE_MEDIA, errorRes: Int, res: Int) {
        val imagelocal = UMImage(activity, res)
        imagelocal.setThumb(UMImage(activity, errorRes)) // 加载失败的图片
        ShareAction(activity).withMedia(imagelocal)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }

    /**
     *  分享网络图片
     */
    fun shareImageNet(activity: Activity, share_media: SHARE_MEDIA, errorRes: Int, res: String) {
        val imageurl = UMImage(activity, res)
        imageurl.setThumb(UMImage(activity, errorRes))  // 加载失败的图片
        ShareAction(activity).withMedia(imageurl)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }

    /**
     *  分享 链接
     */
    fun shareUrl(activity: Activity, share_media: SHARE_MEDIA, shareUrlBean: ShareUrlBean?) {
        shareUrlBean?.apply {
            val web = UMWeb(url)
            web.title = title
            web.setThumb(UMImage(activity, errorRes))
            web.description = "my description"
            ShareAction(activity).withMedia(web)
                .setPlatform(share_media)
                .setCallback(MUMShareListener()).share()
        }

    }

    /**
     *  分享音乐
     */
    fun shareMusic(activity: Activity, share_media: SHARE_MEDIA, shareMusicBean: ShareUrlBean?) {
        shareMusicBean?.apply {
            val music = UMusic(url)
            music.title = title
            music.setThumb(UMImage(activity, errorRes))
            music.description = description
            music.setmTargetUrl(url)
            ShareAction(activity).withMedia(music)
                .setPlatform(share_media)
                .setCallback(MUMShareListener()).share()
        }

    }

    /**
     *  分享视频
     */
    fun shareVideo(activity: Activity, share_media: SHARE_MEDIA, shareVideoBean: ShareVideoBean?) {
        shareVideoBean?.apply {
            val video = UMVideo(url)
            video.setThumb(UMImage(activity, errorRes))
            video.title = title
            video.description = description
            ShareAction(activity).withMedia(video)
                .setPlatform(share_media)
                .setCallback(MUMShareListener()).share()
        }
    }

    fun shareLocalFile(
        activity: Activity,
        share_media: SHARE_MEDIA,
        localfile: File,
        text: String,
        title: String
    ) {
        ShareAction(activity)
            .withFile(localfile)
            .withText(text)
            .withSubject(title)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }

    /**
     *  分享视频
     */
    fun shareLocalVideo(
        activity: Activity,
        share_media: SHARE_MEDIA,
        video: File?,
        text: String,
        title: String
    ) {
        video?.apply {
            val umVideo = UMVideo(this)
            ShareAction(activity)
                .withMedia(umVideo)
                .withText(text)
                .withSubject(title)
                .setPlatform(share_media)
                .setCallback(MUMShareListener()).share()
        }
    }


    /**
     *  分享图文(本地)
     */
    fun shareTextAndImage(
        activity: Activity,
        share_media: SHARE_MEDIA,
        resImage: Int,
        errorRes: Int,
        text: String
    ) {
        val imagelocal = UMImage(activity, resImage)
        imagelocal.setThumb(UMImage(activity, errorRes))
        ShareAction(activity).withText(text)
            .withMedia(imagelocal)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }

    /**
     *  分享图文(网络)
     */
    fun shareTextAndImage(
        activity: Activity,
        share_media: SHARE_MEDIA,
        resImage: String,
        errorRes: Int,
        text: String
    ) {
        val imagelocal = UMImage(activity, resImage)
        imagelocal.setThumb(UMImage(activity, errorRes))
        ShareAction(activity).withText(text)
            .withMedia(imagelocal)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }


    /**
     *  分享多图
     */
    fun shareMulImage(
        activity: Activity,
        share_media: SHARE_MEDIA,
        description: String,
        vararg args: UMImage?
    ) {
        ShareAction(activity).withText(description).withMedias(*args)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }

    /**
     *  分享 emoji
     */
    fun shareEmoji(activity: Activity, share_media: SHARE_MEDIA, resImage: Int, errorRes: Int) {
        val emoji = UMEmoji(activity, resImage)
        emoji.setThumb(UMImage(activity, errorRes))
        ShareAction(activity)
            .withMedia(emoji)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }

    /**
     *  分享微信小程序
     */
    fun shareMINApp(activity: Activity, share_media: SHARE_MEDIA, shareMAppBean: ShareMWXBean) {
        val umMin = UMMin(shareMAppBean.compatibilityH5Path)
        umMin.setThumb(UMImage(activity, shareMAppBean.errorRes))
        umMin.title = shareMAppBean.title
        umMin.description = shareMAppBean.description
        umMin.path = shareMAppBean.url
        umMin.userName = shareMAppBean.appletId
        ShareAction(activity)
            .withMedia(umMin)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }

    /**
     *  分享QQ小程序
     */
    fun shareQQMiniApp(activity: Activity, share_media: SHARE_MEDIA, shareMQQBean: ShareMQQBean) {
        val qqMini = UMQQMini(shareMQQBean.compatibilityH5Path)
        qqMini.setThumb(UMImage(activity, shareMQQBean.errorRes)) // 缩略图支持网络图片和本地图片
        qqMini.title = shareMQQBean.title
        qqMini.description = shareMQQBean.description
        qqMini.miniAppId = shareMQQBean.appletId
        qqMini.path = shareMQQBean.url
        ShareAction(activity)
            .withMedia(qqMini)
            .setPlatform(share_media)
            .setCallback(MUMShareListener()).share()
    }
}