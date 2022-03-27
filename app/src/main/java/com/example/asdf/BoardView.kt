package com.example.asdf

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BoardView(context: Context?, attrs: AttributeSet?): View(context, attrs) {
    val cellSide: Float = 130f

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint()
        paint.color = Color.LTGRAY

        for (row in 0 until 6)
            for (col in 0 until 5)
                canvas?.drawRect(110f + col * cellSide, 210f  + row * cellSide, 100f + (col + 1)* cellSide, 200f + (row + 1) * cellSide, paint)

        }
}