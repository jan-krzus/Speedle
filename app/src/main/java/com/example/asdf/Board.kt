package com.example.asdf

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.annotation.StyleableRes
import kotlin.random.Random

object Board{
//    val board = arrayOf<Array<Boolean>>()
    var size = Pair(5,6)
    var startingPoint = Pair(0,0)
    var board = emptyArray<Array<Boolean>>()

    var tileBoard = arrayOf(
        arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
        arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
        arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
        arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
        arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
        arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY)
    )

    init {
//        fillBoard()
//        assert(size.first > startingPoint.first && size.second > startingPoint.second)
//        assert(board[startingPoint.second][startingPoint.first])
    }

    fun fillBoard(resources: Resources) {
        board = Array(size.second) { Array(size.first) {false} }
        // get random board of boards
        val arrBoards = resources.obtainTypedArray(R.array.boards_5x6)
        val arr = resources.getStringArray(arrBoards.getResourceId(1, -1))

        for (row in arr.withIndex()) {
            for (col in row.value.withIndex()) {
                if (col.value != '0') {
                    if (col.value == '1')
                        startingPoint = Pair(col.index, row.index)
                    board[row.index][col.index] = true
                }
            }
        }

        tileBoard = arrayOf(
            arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
            arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
            arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
            arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
            arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),
            arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY)
        )
    }

    fun isOnTrack(position: Pair<Int, Int>): Boolean {
        return board[position.second][position.first]
    }

}
