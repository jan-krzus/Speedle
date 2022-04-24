package com.example.asdf

import android.content.res.Resources
import android.util.Log
import kotlin.random.Random

object Board{
    var size = Pair(0, 0) //Pair(5,6)
    var startingPoint = Pair(0,0)
    var board = emptyArray<Array<Boolean>>()

    var tileBoard = emptyArray<Array<Tile>>()

    enum class Mode(val size: Pair<Int, Int>) {
        NORMAL(Pair(5, 6)),
        HARD(Pair(10, 12))
    }

    fun initTileBoard(){
        tileBoard = Array(size.second,{i -> Array(size.first, {Tile.EMPTY})})
    }


    fun fillBoard(resources: Resources) {
        initTileBoard()
        board = Array(size.second) { Array(size.first) {false} }
        // get random board of boards
        val arrBoards = if(size.first == 5) resources.obtainTypedArray(R.array.boards_5x6) else resources.obtainTypedArray(R.array.boards_10x12)
        val arr = resources.getStringArray(arrBoards.getResourceId(Random.nextInt(arrBoards.length()), -1))
        for (row in arr.withIndex()) {
            for (col in row.value.withIndex()) {
                if (col.value != '0') {
                    if (col.value == '1')
                        startingPoint = Pair(col.index, row.index)
                    board[row.index][col.index] = true
                }
            }
        }


    }

    fun isOnTrack(position: Pair<Int, Int>): Boolean {
        return board[position.second][position.first]
    }

}
