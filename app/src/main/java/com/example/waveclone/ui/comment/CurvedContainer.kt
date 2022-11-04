package com.example.waveclone.ui.comment

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class CurvedContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    /* paint object for coloring the canvas */
    private val mPaint = Paint()

    /* path that will be drawn to achieve the shape */
    private val path = Path()

    /* arcs will create the effect of curved corners */
    private val leftArc = RectF()
    private val rightArc = RectF()

    /* offset values for our curved corners */
    private val xAxisOffset = 100f
    private val yAxisOffset = 100f

    init {
        /* setting the background as transparent as we're drawing the view ourself */
        setBackgroundColor(Color.TRANSPARENT)
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        mPaint.apply {
            style = Paint.Style.FILL
            color = Color.GRAY
            isAntiAlias = true
            clipChildren = true
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        /* curve from P1 to P2 */
        leftArc.set(
            0f,
            0f,
            xAxisOffset,
            yAxisOffset
        )
        path.addArc(leftArc, 180f, 90f)
        /* line from P2 to P3 */
        path.lineTo(w.toFloat() - (xAxisOffset * 0.5f), 0f)
        /* curve from P3 to P4 */
        rightArc.set(
            w.toFloat() - (xAxisOffset),
            0f,
            w.toFloat(),
            yAxisOffset
        )
        path.addArc(rightArc, 270f, 90f)
        /* line from P4 to P5 */
        path.lineTo(w.toFloat(), h.toFloat())
        /* line from P5 to P6 */
        path.lineTo(0f, h.toFloat())
        /* line from P6 to P1 */
        path.lineTo(0f, yAxisOffset - (yAxisOffset / 2))
        path.close()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, mPaint)
    }

}