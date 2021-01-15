package com.lsn.module.base.wedgit.progress

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.lsn.module.base.R
import kotlin.math.sqrt

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2018/12/12
 * Description
 */
class CustomShapeView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var paint: Paint? = null
    private var path: Path? = null
    private var onTriangleListener: OnTriangleListener? = null

    enum class Shape {
        RECT, CIRCLE, TRIANGLE
    }

    private var tag: Shape =
        Shape.CIRCLE

    init {
        paint = Paint()
        paint?.apply {
            isAntiAlias = true
            color = resources.getColor(R.color.black)
        }

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()


        when (tag) {
            Shape.RECT -> {
                // 画矩形
                paint?.color = resources.getColor(R.color.rect)
                paint?.let {
                    canvas.drawRect(0f, 0f, width, height, it)
                    canvas.drawPoint(pivotX, pivotY, it)
                }
            }
            Shape.CIRCLE -> {
                // 画圆
                paint?.color = resources.getColor(R.color.circle)
                paint?.let {
                    canvas.drawCircle(width / 2, height / 2, width / 2, it)
                }
            }
            Shape.TRIANGLE -> {
                // 画三角形
                paint?.color = resources.getColor(R.color.triangle)
                if (path == null) {
                    path = Path()
                } else {
                    path?.reset()
                }
                (height - (sqrt(3.0) / 2 * width).toFloat()) / 2 - sqrt(3.0) / 2 * width
                path?.apply {
                    moveTo(width / 2, (height - (sqrt(3.0) / 2 * width).toFloat()) / 2)
                    lineTo(
                        0f,
                        ((height - (sqrt(3.0) / 2 * width).toFloat()) / 2 + sqrt(3.0) / 2 * width).toFloat()
                    )
                    lineTo(
                        width,
                        ((height - (sqrt(3.0) / 2 * width).toFloat()) / 2 + sqrt(3.0) / 2 * width).toFloat()
                    )
                    close()
                    paint?.let {
                        canvas.drawPath(this, it)
                    }

                }


            }
        }
    }


    fun changeShape() {

        when (tag) {
            Shape.CIRCLE -> {
                tag = Shape.RECT
                pivotX = measuredWidth / 2.toFloat()
                pivotX = measuredWidth / 2.toFloat()
                onTriangleListener?.onTriangleListener(tag)
                ObjectAnimator.ofFloat(this, "rotation", 0f, 180f).start()
            }
            Shape.RECT -> {

                // 因为等边三角形的特殊性
                tag = Shape.TRIANGLE
                pivotX = measuredWidth / 2 + (height - (Math.sqrt(3.0) / 2 * width).toFloat()) / 2
                pivotY = measuredHeight / 2 + (height - (Math.sqrt(3.0) / 2 * width).toFloat()) / 2
                onTriangleListener?.onTriangleListener(tag)
                ObjectAnimator.ofFloat(this, "rotation", 0f, 120f).start()
            }
            Shape.TRIANGLE -> {
                tag = Shape.CIRCLE
                pivotX = measuredWidth / 2.toFloat()
                pivotX = measuredWidth / 2.toFloat()
                onTriangleListener?.onTriangleListener(tag)
                ObjectAnimator.ofFloat(this, "rotation", 0f, 180f).start()
            }
        }
        invalidate()
    }


    interface OnTriangleListener {
        fun onTriangleListener(tag: Shape)
    }

    fun setOnTriangleListener(onTriangleListener: OnTriangleListener) {
        this.onTriangleListener = onTriangleListener
    }

}