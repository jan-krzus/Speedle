package com.example.asdf

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class BoardView(context: Context?, attrs: AttributeSet?): View(context, attrs) {
    var res: Resources = resources
    var board = Board
    val cellSide: Float = if(board.size.first == 5) 190f else 95f

    override fun onDraw(canvas: Canvas?) {
        for (row in 0 until board.size.second) {
            for (col in 0 until board.size.first) {
                var bitmap = BitmapFactory.decodeResource(res, board.tileBoard[row][col].imageId)
                var bitmaps = Bitmap.createScaledBitmap(bitmap,cellSide.toInt(),cellSide.toInt(),false)
                canvas?.drawBitmap(bitmaps,0f + (col * cellSide),0f + (row * cellSide),Paint())
            }
        }
    }
}
