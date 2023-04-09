package com.a5k.tasksheduler.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_MOVE
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.a5k.tasksheduler.util.toCoordinate

class CalendarViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var maxHeight = 0
    private var maxWidth = 0


    private var listTask = listOf<TaskView>()
    private var listCell = listOf<CellCalendarView>()


    fun initCell(listCell: List<CellCalendarView>) {
        this.listCell = listCell
        maxHeight = 0
        setCell()
    }

    private fun setCell() {
        maxHeight = 0
        listCell.forEach { cellView ->
            this.addView(cellView)
            maxWidth += cellView.width
            maxHeight += cellView.heightLineVertical.toInt()
        }
    }

    fun initTask(listTask: List<TaskView>) {
        this.removeAllViews()
        this.listTask = listTask
        setCell()
        listTask.forEach { taskView ->
            this.addView(taskView)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val resolveWidth = resolveSize(maxWidth, widthMeasureSpec)
        val resolveHeight = resolveSize(maxHeight, heightMeasureSpec)
        setMeasuredDimension(resolveWidth, resolveHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        var heightVerticalLine = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            when ((child as TypeCustomView).getType()) {
                ShedulerType.CALENDAR -> {
                    child.id = i
                    child.layout(l, heightVerticalLine, r, b)
                    heightVerticalLine += (child as CellCalendarView).heightLineVertical.toInt()
                }
                ShedulerType.TASK -> {
                    child.layout(l, t, r, b)
                }
            }
        }
    }
}

