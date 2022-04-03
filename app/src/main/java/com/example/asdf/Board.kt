package com.example.asdf

class Board(val size: Pair<Int, Int>, val startingPoint: Pair<Int, Int>) {
//    val board = arrayOf<Array<Boolean>>()
    val board = arrayOf(arrayOf(true,true,true,true,true,),arrayOf(false,false,false,false,false,),arrayOf(false,false,false,false,false,),arrayOf(false,false,false,false,false,),arrayOf(false,false,false,false,false,),arrayOf(false,false,false,false,false,))
    val tileBoard = arrayOf(arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY),arrayOf(Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY,Tile.EMPTY))

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
