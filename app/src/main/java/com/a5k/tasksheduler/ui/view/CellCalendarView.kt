package com.a5k.tasksheduler.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.a5k.tasksheduler.R


class CellCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), TypeCustomView {

    // Vertical line
    private var startLineVerticalY = 0f
    val heightLineVertical = 100f

    //text
    private val timeStartX = 10f
    private var time = "00:00"

    // Horizontal line
    private var startLineHorizontalY = startLineVerticalY
    private var longHorizontal = 0

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.black200)
        strokeWidth = 5f
    }

    private var textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.black)
        textSize = 36f
    }

    //размеры графа
    private val contentWidth = resources.getDimensionPixelSize(R.dimen.graph_width)
    private var contentHeight = resources.getDimensionPixelSize(R.dimen.graph_height)

    fun setting(time: String) {
        this.time = time
       /* requestLayout()
        invalidate()*/
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val resolveWidth = resolveSize(contentWidth, widthMeasureSpec)
        val resolveHeight = resolveSize(contentHeight, heightMeasureSpec)
        setMeasuredDimension(resolveWidth, resolveHeight)
        longHorizontal = resolveWidth
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        drawTasks()
    }

    private fun Canvas.drawTasks() {
        val heightText = textPaint.textSize
        val wightTextX = textPaint.getTextWidths(time,  FloatArray(time.length)).toFloat() * 23
        drawText(time, timeStartX, heightText, textPaint)
        drawLine(wightTextX, startLineVerticalY, wightTextX, heightLineVertical, linePaint)
        drawLine(wightTextX, startLineHorizontalY, longHorizontal.toFloat(), startLineHorizontalY, linePaint)
    }

    override fun getType() = ShedulerType.CALENDAR
}