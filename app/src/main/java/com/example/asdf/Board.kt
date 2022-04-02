package com.example.asdf

class Board(val size: Pair<Int, Int>, val startingPoint: Pair<Int, Int>) {
    val board = arrayOf<Array<Boolean>>()
    val tileBoard = arrayOf<Array<Tile>>()

    init {
        fillBoard()
        assert(size.first > startingPoint.first && size.second > startingPoint.second)
        assert(board[startingPoint.second][startingPoint.first])
    }

    fun fillBoard() {
        // TODO: implement import array from R.array or sth
    }

    fun isOnTrack(position: Pair<Int, Int>): Boolean {
        return board[position.second][position.first]
    }
}
