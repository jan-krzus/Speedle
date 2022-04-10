package com.example.asdf

object Board {
//    val board = arrayOf<Array<Boolean>>()
    var size = Pair(5,6)
    var startingPoint = Pair(0,0)
    var board = arrayOf(
        arrayOf(true, false, false, false, false),
        arrayOf(true, true, false, false, false),
        arrayOf(false, true, false, true, true),
        arrayOf(false, true, false, true, false),
        arrayOf(false, true, false, true, false),
        arrayOf(false, true, true, true, false),
    )
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

    fun fillBoard() {
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

    var sampleBoard = arrayOf(
        arrayOf(true, false, false, false, false),
        arrayOf(true, true, false, false, false),
        arrayOf(false, true, false, true, true),
        arrayOf(false, true, false, true, false),
        arrayOf(false, true, false, true, false),
        arrayOf(false, true, true, true, false),
    )
}
