package com.a5k.tasksheduler.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_MOVE
import android.widget.LinearLayout
import com.a5k.tasksheduler.util.toCoordinate

class CalendarViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var maxHeight = 0
    private var heightVerticalLine = 0

    private var arrayTask = mutableListOf<Int>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val countChild = childCount
        maxHeight = 0
        var maxWidth = 0

        for (i in 0 until countChild) {
            val child = getChildAt(i)
            if ((child as TypeCustomView).getType() == ShedulerType.CALENDAR) {
                maxWidth += child.width
                maxHeight += (child as CellCalendarView).heightLineVertical.toInt()
            }
        }
        val resolveWidth = resolveSize(maxWidth, widthMeasureSpec)
        val resolveHeight = resolveSize(maxHeight, heightMeasureSpec)

        setMeasuredDimension(resolveWidth, maxHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        for (i in 1..childCount) {
            val child = getChildAt(i - 1)

            when ((child as TypeCustomView).getType()) {
                ShedulerType.CALENDAR -> {
                    child.id = i
                    child.layout(l, heightVerticalLine, r, b)
                    heightVerticalLine += (child as CellCalendarView).heightLineVertical.toInt()
                }
                ShedulerType.TASK -> {
                   child.id = i
                   (child as TaskView).task?.dateStart?.toCoordinate(b)?.let { task ->
                       arrayTask.add(task)
                   }
                   child.layout(l, t, r, b)
                }
            }
        }
    }
}

