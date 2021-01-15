package com.lsn.base.widget.navigation

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lsn.base.R
import com.lsn.lib_util.util.BarUtils
import com.lsn.lib_util.util.ConvertUtils
import java.lang.ref.WeakReference

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2019/1/9
 * Description  将一些常用的Toolbar封装
 *  1.颜色
 */
class EasyNavigation private constructor() {


    enum class NavigationMode {
        IMMERSION,              // 沉浸式 , 用于渐变，图片等比较复杂的沉浸式
        SIMPLE_IMMERSION,       // 沉浸式 , 用于简单一种颜色
        SEPARATE                // 分离模式 , 状态栏与 Toolbar 不同颜色
    }

    enum class TitleMode {
        CENTER,                 // 标题居中
        LIFT                    // 标题居坐
    }


    private enum class Tag {
        NB_ROOT, LL_BACK, IV_BACK, TV_LIFT_TITLE, TV_CENTER_TITLE, IV_RIGHT2, TV_RIGHT2, IV_RIGHT
    }

    private var isDefault = true                             // 默认 View 模式
    private var mNavigationMode: NavigationMode =
        NavigationMode.SIMPLE_IMMERSION // 默认沉浸式 , 用于简单一种颜色
    private var isFinish = true                              // 默认点击返回关闭 activity
    private var mRootViewHeight = 0
    private var mStatusBarColor: Int = 0
    private var mCustomView: View? = null                    // 自定义View
    private var mBackground: Int = 0
    private var mTitleMode: TitleMode = TitleMode.CENTER      // 默认居中
    private var mIsStatusBarHide = false                      // 状态栏是否影藏
    private var mTitle: String? = ""                          // 标题
    private var mTitleColor = 0                               // 标题字体颜色
    private var mTitleSize = 0                                // 标题字体颜色
    private var mTvRightText: String? = ""                   // 右侧的文字
    private var mTvRightColor = 0                             // 右侧的文字颜色
    private var mTvRightSize = 0                              // 右侧的文字颜色
    private var mIcBackIcon = 0                               // back 的图片
    private var mIcIvRight2Icon = 0                           // 最右侧的图片
    private var mIcIvRightIcon = 0                            // 右侧图片
    private var isShowBack = true                             // 是否显示 back


    class Builder(context: Context) {
        private var mContext: Context?
        private var mEasyNavigation: EasyNavigation
        private var mDefaultView =
            LayoutInflater.from(context).inflate(R.layout.view_default_navigation, null, true)

        init {
            val wContext = WeakReference<Context>(context)
            mContext = wContext.get()
            mEasyNavigation = EasyNavigation()
        }

        fun isFinish(isFinish: Boolean): Builder {
            mEasyNavigation.isFinish = isFinish
            return this
        }

        fun isShowBack(showBack: Boolean): Builder {
            mEasyNavigation.isShowBack = showBack
            return this
        }

        fun isShowBack(showBack: Boolean, resource: Int): Builder {
            mEasyNavigation.isShowBack = showBack
            mEasyNavigation.mIcBackIcon = resource
            return this
        }

        /**
         *  是否为设置为沉浸式
         */
        fun setNavigationMode(navigationMode: NavigationMode): Builder {
            mEasyNavigation.mNavigationMode = navigationMode
            return this
        }

        /**
         *  设置状态栏的颜色
         */
        fun setStatusBarColor(color: Int): Builder {
            if (color != 0) {
                mContext?.let {
                    mEasyNavigation.mStatusBarColor = color
                }

            }
            return this
        }

        fun setView(resourceID: View?): Builder {
            if (resourceID != null) {
                mEasyNavigation.mCustomView = resourceID
            }
            return this
        }


        fun setBackground(resource: Int): Builder {
            if (resource != 0) {
                mEasyNavigation.mBackground = resource
            }
            return this
        }

        /**
         *  是否影藏状态栏
         */
        fun isHideStatusBar(isHide: Boolean): Builder {
            if (isSeparate()) {
                mEasyNavigation.mIsStatusBarHide = isHide
            }
            return this
        }


        fun setTitleMode(titleMode: TitleMode): Builder {
            this.mEasyNavigation.mTitleMode = titleMode
            return this
        }


        fun setBackIcon(icIcon: Int): Builder {
            mContext?.let {
                mEasyNavigation.mIcBackIcon = icIcon
            }
            return this
        }

        fun setIvRight2Icon(icIcon: Int): Builder {
            mContext?.let {
                mEasyNavigation.mIcIvRight2Icon = icIcon
            }
            return this
        }


        fun setTitle(title: String?): Builder {
            if (title != null) {
                mEasyNavigation.mTitle = title
            }
            return this
        }

        fun setTitleColor(color: Int): Builder {
            if (color != 0) {
                mEasyNavigation.mTitleColor = color
            }
            return this
        }

        fun setTitleSize(size: Int): Builder {
            if (size != 0) {
                mEasyNavigation.mTitleSize = size
            }
            return this
        }

        fun setTitleInfo(title: String?, color: Int): Builder {
            if (title != null) {
                mEasyNavigation.mTitle = title
            }
            if (color != 0) {
                mEasyNavigation.mTitleColor = color
            }
            return this
        }

        fun setTitleInfo(title: String?, color: Int, size: Int): Builder {
            if (title != null) {
                mEasyNavigation.mTitle = title
            }

            if (color != 0) {
                mEasyNavigation.mTitleColor = color
            }

            if (size != 0) {
                mEasyNavigation.mTitleSize = size
            }
            return this
        }

        fun setTvRightText(text: String?): Builder {
            mContext?.let {
                mEasyNavigation.mTvRightText = text
            }
            return this
        }

        fun setTvRightColor(color: Int): Builder {
            if (color != 0) {
                mEasyNavigation.mTvRightColor = color
            }
            return this
        }

        fun setTvRightSize(size: Int): Builder {
            if (size != 0) {
                mEasyNavigation.mTvRightSize = size
            }
            return this
        }

        fun setTvRightInfo(text: String?, color: Int): Builder {
            mContext?.let {
                mEasyNavigation.mTvRightText = text
            }

            if (color != 0) {
                mEasyNavigation.mTvRightColor = color
            }
            return this
        }

        fun setTvRightInfo(text: String?, color: Int, size: Int): Builder {
            mContext?.let {
                mEasyNavigation.mTvRightText = text
            }

            if (color != 0) {
                mEasyNavigation.mTvRightColor = color
            }

            if (size != 0) {
                mEasyNavigation.mTvRightSize = size
            }
            return this
        }


        fun setIvRightIcon(icIcon: Int): Builder {
            mContext?.let {
                mEasyNavigation.mIcIvRightIcon = icIcon
            }
            return this
        }

        fun setViewWidgetClickListener(
            widgetID: Int,
            iViewWidgetClickListener: IViewWidgetClickListener
        ) {
            val view = mEasyNavigation.mCustomView?.findViewById<View>(widgetID)
            view?.setOnClickListener {
                iViewWidgetClickListener.onCustomViewClick(view)
            }
        }


        fun setOnBackClickListener(iOnBackClickListener: IOnBackClickListener): Builder {
            findDefaultWidgetByTag(Tag.LL_BACK)?.setOnClickListener {
                mContext?.let {
                    val activity = mContext as AppCompatActivity
                    if (mEasyNavigation.isFinish) {
                        activity.onBackPressed()
                    }
                }
                iOnBackClickListener.onClick(it)
            }
            return this
        }

        fun setOnIvRight2ClickListener(iOnIvRight2ClickListener: IOnIvRight2ClickListener): Builder {
            findDefaultWidgetByTag(Tag.IV_RIGHT2)?.setOnClickListener {
                iOnIvRight2ClickListener.onClick(it)
            }
            return this
        }

        fun setOnTvRightClickListener(iOnTvRightClickListener: IOnTvRightClickListener): Builder {
            findDefaultWidgetByTag(Tag.TV_RIGHT2)?.setOnClickListener {
                iOnTvRightClickListener.onClick(it)
            }
            return this
        }

        fun setOnnIvRightClickListener(iOnIvRightClickListener: IOnIvRightClickListener): Builder {
            findDefaultWidgetByTag(Tag.IV_RIGHT)?.setOnClickListener {
                iOnIvRightClickListener.onClick(it)
            }
            return this
        }


        fun setRootViewHeight(height: Int) {
            if (height != 0) {
                mEasyNavigation.mRootViewHeight = height
            }
        }


        fun build(): EasyNavigation {

            if (mContext != null) {
                val activity = mContext as Activity

                // 获取 decorView 最顶层
                val flDecorView = activity.window.decorView as ViewGroup
                val llParent = flDecorView.getChildAt(0) as ViewGroup

                if (mEasyNavigation.isShowBack) {
                    setOnBackClickListener(object : EasyNavigation.Builder.IOnBackClickListener {
                        override fun onClick(v: View) {
                            activity.onBackPressed()
                        }
                    })
                }

                if (mEasyNavigation.isShowBack) {
                    val imageView = findDefaultWidgetByTag(Tag.LL_BACK) as LinearLayout
                    imageView.visibility = View.VISIBLE
                } else {
                    val imageView = findDefaultWidgetByTag(Tag.LL_BACK) as LinearLayout
                    imageView.visibility = View.GONE
                }


                if (mEasyNavigation.mRootViewHeight != 0) {
                    mDefaultView.layoutParams =
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            mEasyNavigation.mRootViewHeight
                        )
                } else {
                    mContext?.let {
                        mDefaultView.layoutParams =
                            ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ConvertUtils.dp2px(56f)
                            )
                    }
                }

                if (isDefault()) {
                    llParent.addView(mDefaultView, 0)
                } else {
                    if (mEasyNavigation.mCustomView != null) {
                        llParent.addView(mEasyNavigation.mCustomView, 0)
                    }
                }


                // 设置状态栏颜色
                if (mEasyNavigation.mStatusBarColor != 0 && isSeparate() && isDefault()) {
                    mContext?.let {
                        //StatusBarUtils.setStatusBarColor(activity, it.resources.getColor(mEasyNavigation.mStatusBarColor))
                    }
                }

                // 移除状态栏
                if (mEasyNavigation.mIsStatusBarHide && !isImmersion() && isDefault()) {
                    //StatusBarUtils.removeStatusBar(activity)
                }

                // 设置复杂沉浸式背景颜色
                if (mEasyNavigation.mBackground != 0 && isDefault()) {
                    if (isImmersion()) {
                        // 复杂的状态栏

                        // 1. 移除状态栏
                        //StatusBarUtils.removeStatusBar(activity)
                        // 2. 重新计算并设置 Navigation 的高度
                        val relativeLayout = findDefaultWidgetByTag(Tag.NB_ROOT) as LinearLayout
                        var statusBarHeight = BarUtils.getStatusBarHeight()
                        // 1 .测量 toolbar 真实高度 及 上半部布局高度，
                        //relativeLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
                        //relativeLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
                        val params = relativeLayout.layoutParams
                        // 添加高度
                        params.height += statusBarHeight
                        // 设置 padding
                        relativeLayout.setPadding(0, statusBarHeight, 0, 0)
                        relativeLayout.setBackgroundResource(mEasyNavigation.mBackground)


                    } else if (isSimpleImmersion()) {
                        // 简单的样式
                        val relativeLayout = findDefaultWidgetByTag(Tag.NB_ROOT) as LinearLayout

                        // 1. 设置状态栏的颜色
                        /*mContext?.let {
                            //setStatusBarColor(mEasyNavigation.mBackground)
                            StatusBarUtils.setStatusBarColor(
                                activity,
                                it.resources.getColor(mEasyNavigation.mBackground)
                            )
                        }*/

                        // 2. 设置 navigation  的颜色
                        relativeLayout.setBackgroundResource(mEasyNavigation.mBackground)
                    } else {
                        val relativeLayout = findDefaultWidgetByTag(Tag.NB_ROOT) as LinearLayout
                        // 分离模式
                        relativeLayout.setBackgroundResource(mEasyNavigation.mBackground)
                    }
                }

                // 设置标题
                if (!TextUtils.isEmpty(mEasyNavigation.mTitle) && isDefault()) {
                    val title: TextView
                    if (mEasyNavigation.mTitleMode == TitleMode.CENTER) {
                        // 居中
                        if (findDefaultWidgetByTag(Tag.TV_CENTER_TITLE) != null) {
                            title = findDefaultWidgetByTag(Tag.TV_CENTER_TITLE) as TextView
                            title.visibility = View.VISIBLE
                            title.text = mEasyNavigation.mTitle
                        }
                    } else {
                        // 左边
                        if (findDefaultWidgetByTag(Tag.TV_LIFT_TITLE) != null) {
                            title = findDefaultWidgetByTag(Tag.TV_LIFT_TITLE) as TextView
                            title.visibility = View.VISIBLE
                            title.text = mEasyNavigation.mTitle
                        }
                    }
                }

                if (mEasyNavigation.mTitleColor != 0 && isDefault()) {
                    val title: TextView
                    if (mEasyNavigation.mTitleMode == TitleMode.CENTER) {
                        // 居中
                        if (findDefaultWidgetByTag(Tag.TV_CENTER_TITLE) != null) {
                            title = findDefaultWidgetByTag(Tag.TV_CENTER_TITLE) as TextView
                            mContext?.let {
                                title.setTextColor(it.resources.getColor(mEasyNavigation.mTitleColor))
                            }
                        }
                    } else {
                        // 左边
                        if (findDefaultWidgetByTag(Tag.TV_LIFT_TITLE) != null) {
                            title = findDefaultWidgetByTag(Tag.TV_LIFT_TITLE) as TextView
                            mContext?.let {
                                title.setTextColor(it.resources.getColor(mEasyNavigation.mTitleColor))
                            }
                        }
                    }
                }


                if (mEasyNavigation.mTitleSize != 0 && isDefault()) {
                    val title: TextView
                    if (mEasyNavigation.mTitleMode == TitleMode.CENTER) {
                        // 居中
                        if (findDefaultWidgetByTag(Tag.TV_CENTER_TITLE) != null) {
                            title = findDefaultWidgetByTag(Tag.TV_CENTER_TITLE) as TextView
                            mContext?.let {
                                title.textSize =
                                    ConvertUtils.dp2px((mEasyNavigation.mTitleSize).toFloat())
                                        .toFloat()
                            }
                        }
                    } else {
                        // 左边
                        if (findDefaultWidgetByTag(Tag.TV_LIFT_TITLE) != null) {
                            title = findDefaultWidgetByTag(Tag.TV_LIFT_TITLE) as TextView
                            mContext?.let {
                                title.textSize =
                                    ConvertUtils.dp2px((mEasyNavigation.mTitleSize).toFloat())
                                        .toFloat()
                            }
                        }
                    }
                }

                // 设置最右边的文字
                if (!TextUtils.isEmpty(mEasyNavigation.mTvRightText) && isDefault()) {
                    if (findDefaultWidgetByTag(Tag.TV_RIGHT2) != null) {
                        val title: TextView = findDefaultWidgetByTag(Tag.TV_RIGHT2) as TextView
                        title.visibility = View.VISIBLE
                        title.text = mEasyNavigation.mTvRightText
                    }
                }

                if (mEasyNavigation.mTvRightColor != 0 && isDefault()) {
                    if (findDefaultWidgetByTag(Tag.TV_RIGHT2) != null) {
                        val title: TextView = findDefaultWidgetByTag(Tag.TV_RIGHT2) as TextView
                        mContext?.let {
                            title.setTextColor(it.resources.getColor(mEasyNavigation.mTvRightColor))
                        }
                    }
                }

                if (mEasyNavigation.mTvRightSize != 0 && isDefault()) {
                    if (findDefaultWidgetByTag(Tag.TV_RIGHT2) != null) {
                        val title: TextView = findDefaultWidgetByTag(Tag.TV_RIGHT2) as TextView
                        mContext?.let {
                            title.textSize =
                                ConvertUtils.dp2px((mEasyNavigation.mTvRightSize).toFloat())
                                    .toFloat()
                        }
                    }
                }

                // 设置返回图片
                if (mEasyNavigation.mIcBackIcon != 0) {
                    mContext?.let {
                        if (findDefaultWidgetByTag(Tag.IV_BACK) != null) {
                            val imageView = findDefaultWidgetByTag(Tag.IV_BACK) as ImageView
                            imageView.visibility = View.VISIBLE
                            imageView.setImageResource(mEasyNavigation.mIcBackIcon)
                        }
                    }
                }

                if (mEasyNavigation.mIcIvRight2Icon != 0) {
                    mContext?.let {
                        if (findDefaultWidgetByTag(Tag.IV_RIGHT2) != null) {
                            val imageView = findDefaultWidgetByTag(Tag.IV_RIGHT2) as ImageView
                            imageView.visibility = View.VISIBLE
                            imageView.setImageResource(mEasyNavigation.mIcIvRight2Icon)
                        }
                    }
                }

                if (mEasyNavigation.mIcIvRightIcon != 0) {
                    mContext?.let {
                        if (findDefaultWidgetByTag(Tag.IV_RIGHT) != null) {
                            val imageView = findDefaultWidgetByTag(Tag.IV_RIGHT) as ImageView
                            imageView.visibility = View.VISIBLE
                            imageView.setImageResource(mEasyNavigation.mIcIvRightIcon)
                        }
                    }
                }
            }
            return mEasyNavigation
        }

        interface IOnBackClickListener {
            fun onClick(v: View)
        }

        interface IOnIvRight2ClickListener {
            fun onClick(v: View)
        }

        interface IOnTvRightClickListener {
            fun onClick(v: View)
        }

        interface IOnIvRightClickListener {
            fun onClick(v: View)
        }

        interface IViewWidgetClickListener {
            fun onCustomViewClick(view: View)
        }


        private fun isSeparate(): Boolean {
            return mEasyNavigation.mNavigationMode == NavigationMode.SEPARATE
        }

        private fun isImmersion(): Boolean {
            return mEasyNavigation.mNavigationMode == NavigationMode.IMMERSION
        }

        private fun isSimpleImmersion(): Boolean {
            return mEasyNavigation.mNavigationMode == NavigationMode.SIMPLE_IMMERSION
        }

        private fun isDefault(): Boolean {
            return mEasyNavigation.isDefault
        }


        private fun findDefaultWidgetByTag(tag: Tag): View? {
            return when (tag) {
                Tag.NB_ROOT -> mDefaultView?.findViewById(R.id.nbRoot)
                Tag.LL_BACK -> mDefaultView?.findViewById(R.id.llBack)
                Tag.IV_BACK -> mDefaultView?.findViewById(R.id.ivBack)
                Tag.TV_LIFT_TITLE -> mDefaultView?.findViewById(R.id.tvLiftTitle)
                Tag.TV_CENTER_TITLE -> mDefaultView?.findViewById(R.id.tvCenterTitle)
                Tag.IV_RIGHT2 -> mDefaultView?.findViewById(R.id.ivRight2)
                Tag.TV_RIGHT2 -> mDefaultView?.findViewById(R.id.tvRight2)
                Tag.IV_RIGHT -> mDefaultView?.findViewById(R.id.ivRight)
            }
        }

    }
}