package com.example.asdf

object Board {
//    val board = arrayOf<Array<Boolean>>()
    var size = Pair(6,5)
    var startingPoint = Pair(0,0)
    var board = arrayOf(arrayOf(true,true,true,true,true),arrayOf(false,false,false,false,false),arrayOf(false,false,false,false,false),arrayOf(false,false,false,false,false),arrayOf(false,false,false,false,false),arrayOf(false,false,false,false,false))
    var tileBoard = arrayOf(arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY))

    init {
        fillBoard()
        assert(size.first > startingPoint.first && size.second > startingPoint.second)
        assert(board[startingPoint.second][startingPoint.first])
    }

    fun fillBoard() {

    }

    fun isOnTrack(position: Pair<Int, Int>): Boolean {
        return board[position.second][position.first]
    }
}
