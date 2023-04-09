package com.a5k.tasksheduler.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.a5k.tasksheduler.R
import com.a5k.tasksheduler.domain.entity.Task
import com.a5k.tasksheduler.util.PATTERN_TIME
import com.a5k.tasksheduler.util.toCoordinate
import com.a5k.tasksheduler.util.toStringTime


class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), TypeCustomView {

    private val startX = 230f

    //размеры графа
    private val contentWidth = resources.getDimensionPixelSize(R.dimen.graph_width)
    private var contentHeight = resources.getDimensionPixelSize(R.dimen.graph_height)

    //title task
    private val leftMarginTask = 10f
    private val leftMarginTaskDescription = 25f

    private val rightMarginTime = 10f

    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = context.getColor(R.color.blue_cream)
        strokeWidth = 10f
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.black)
        textSize = 42f
    }

    private val textTimePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.blue_dark)
        textSize = 38f
    }

    private val textDescriptionPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.black200)
        textSize = 37f
    }
    
    var task: Task? = null
    private var heightCalendar: Int = 2400
    private var widthTask = 0

    fun settingViewTask(task: Task) {
        this.task = task
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val resolveWidth = resolveSize(contentWidth, widthMeasureSpec)
        val resolveHeight = resolveSize(contentHeight, heightMeasureSpec)
        widthTask = resolveWidth - startX.toInt()
        setMeasuredDimension(resolveWidth, resolveHeight)
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        drawTasks()
    }

    private fun Canvas.drawTasks() {
        task?.let { task ->
            val startYCoordinateTask = task.dateStart.toCoordinate(heightCalendar)
            val endYCoordinateTask = task.dateFinish.toCoordinate(heightCalendar)
            val heightTextTitle = textPaint.textSize

            val startTime = task.dateStart.toStringTime(PATTERN_TIME)
            val endTime =task.dateFinish.toStringTime(PATTERN_TIME)

            val heightTextTime = textTimePaint.textSize
            val measureTextTime = textTimePaint.measureText(startTime)

            val topCoordinateTextStartTimeY = startYCoordinateTask.toFloat()+heightTextTime
            val bottomCoordinateTextStartTimeY = startYCoordinateTask.toFloat()+heightTextTime*2.2
            val topCoordinateEndTextTime = endYCoordinateTask.toFloat()

            val startCoordinateTextStartTimeX = startX - measureTextTime - rightMarginTime

            //время задачи не накладывались друг на друга
            if ((topCoordinateEndTextTime - bottomCoordinateTextStartTimeY) > 0){
                drawText(endTime,
                    startCoordinateTextStartTimeX,
                    topCoordinateEndTextTime,
                    textTimePaint
                )
            }

            drawText(startTime,
                startCoordinateTextStartTimeX,
                topCoordinateTextStartTimeY,
                textTimePaint
                )

            drawText(task.name,
                startX + leftMarginTask,
                startYCoordinateTask + heightTextTitle,
                textPaint)

            val descriptionLength = task.description.length
            if (descriptionLength <= 40) {
                drawText(
                    task.description,
                    startX + leftMarginTaskDescription,
                    startYCoordinateTask + heightTextTitle * 2,
                    textDescriptionPaint
                )
            } else {
                drawText(
                    task.description.substring(0,40).plus("..."),
                    startX + leftMarginTaskDescription,
                    startYCoordinateTask + heightTextTitle * 2,
                    textDescriptionPaint
                )
            }

            drawLine(startX,
                startYCoordinateTask.toFloat(),
                startX,
                endYCoordinateTask.toFloat() ,
                rectPaint )
        }
    }

    override fun getType() = ShedulerType.TASK
}

