package com.example.asdf

import android.content.res.Resources
import android.util.Log
import kotlin.random.Random

object Board{
    var size = Pair(0, 0) //Pair(5,6)
    var startingPoint = Pair(0,0)
    var board = emptyArray<Array<Boolean>>()
    var directionBoard = emptyArray<Array<GameActivity.Direction?>>()
//    var mode = Board.Mode.NORMAL

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

    fun generateBoard(sizeX: Int, sizeY: Int, pathLen: Int) {
        do {
            var generate = false
            directionBoard = Array(sizeY, { i -> Array(sizeX, { null }) })
            val startX = Random.nextInt(sizeX)
            val startY = Random.nextInt(sizeY)

            var currPosition = Pair(startX, startY)

            var prevDir: GameActivity.Direction? = null
            var nextDirList = GameActivity.Direction.values().toMutableList()
            var i = 0;
            while (i < pathLen) {
                if (prevDir != null) {
                    nextDirList.remove(prevDir)
                }
                if (nextDirList.isEmpty()) {
                    generate = true
                    break
                }

                val nextDir = nextDirList.random()

                when (nextDir) {
                    GameActivity.Direction.LEFT -> {
                        if (
                            (currPosition.first <= 0)
                            or (directionBoard[currPosition.second][currPosition.first - 1] != null)
                            or (prevDir == GameActivity.Direction.RIGHT)
                        ) {
                            nextDirList.remove(nextDir)
                            continue
                        }
                        directionBoard[currPosition.second][currPosition.first] = nextDir
                        currPosition = Pair(currPosition.first-1, currPosition.second)
                    }
                    GameActivity.Direction.RIGHT -> {
                        if (
                            (currPosition.first >= sizeX-1)
                            or (directionBoard[currPosition.second][currPosition.first + 1] != null)
                            or (prevDir == GameActivity.Direction.LEFT)
                        ) {
                            nextDirList.remove(nextDir)
                            continue
                        }
                        directionBoard[currPosition.second][currPosition.first] = nextDir
                        currPosition = Pair(currPosition.first + 1, currPosition.second)
                    }
                    GameActivity.Direction.UP -> {
                        if (
                            (currPosition.second <= 0)
                            or (directionBoard[currPosition.second - 1][currPosition.first] != null)
                            or (prevDir == GameActivity.Direction.DOWN)
                        ) {
                            nextDirList.remove(nextDir)
                            continue
                        }
                        directionBoard[currPosition.second][currPosition.first] = nextDir
                        currPosition = Pair(currPosition.first, currPosition.second - 1)
                    }
                    GameActivity.Direction.DOWN -> {
                        if (
                            (currPosition.first >= sizeY-1)
                            or (directionBoard[currPosition.second + 1][currPosition.first] != null)
                            or (prevDir == GameActivity.Direction.UP)
                        ) {
                            nextDirList.remove(nextDir)
                            continue
                        }
                        directionBoard[currPosition.second][currPosition.first] = nextDir
                        currPosition = Pair(currPosition.first, currPosition.second + 1)
                    }
                }
                i++
                nextDirList = GameActivity.Direction.values().toMutableList()
                prevDir = nextDir
            }
        } while (generate)
    }

}
