package com.a5k.tasksheduler.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.a5k.tasksheduler.R


class CalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), TypeCustomView {

    // Vertical line
    private var startLineVerticalY = 0f
    private val wightTextX = 100f
    val heightLineVertical = 100f

    //text
    val timeTextStartX = 10f

    private var text = "12:30"

    // Horizontal line
    private var startLineHorizontalY = startLineVerticalY
    val longHorizontal = 500f

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.green)
        strokeWidth = 6f
    }

    private var textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.teal_700)
        textSize = 32f
    }

    //размеры графа
    private val contentWidth = resources.getDimensionPixelSize(R.dimen.graph_width)
    private var contentHeight = resources.getDimensionPixelSize(R.dimen.graph_height)

    fun setting(text: String) {
        this.text = text
        requestLayout()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val resolveWidth = resolveSize(contentWidth, widthMeasureSpec)
        val resolveHeight = resolveSize(contentHeight, heightMeasureSpec)
        setMeasuredDimension(resolveWidth, resolveHeight)
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        drawTasks()
    }

    private fun Canvas.drawTasks() {
        val heightText = textPaint.textSize
        drawText(text, timeTextStartX, heightText, textPaint)
        drawLine(wightTextX, startLineVerticalY, wightTextX, heightLineVertical, linePaint)
        drawLine(wightTextX, startLineHorizontalY, longHorizontal, startLineHorizontalY, linePaint)
    }

    override fun getType() = CustomType.CALENDAR
}