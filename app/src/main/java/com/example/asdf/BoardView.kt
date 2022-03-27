package com.example.asdf

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class BoardView(context: Context?, attrs: AttributeSet?): View(context, attrs) {
    val cellSide: Float = 130f
    var res: Resources = resources
    var bitmap = BitmapFactory.decodeResource(res, R.drawable.tile_empty_light)
    var bitmaps = Bitmap.createScaledBitmap(bitmap,135,135,false)

    override fun onDraw(canvas: Canvas?) {
        for (row in 0 until 6) {
            for (col in 0 until 5) {
                canvas?.drawBitmap(bitmaps,150f + (col * cellSide),150f + (row * cellSide),Paint())
            }
        }
    }
}