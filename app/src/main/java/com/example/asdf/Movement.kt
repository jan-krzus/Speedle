package com.example.asdf

import android.view.View

class Movement(var board: Board) {
    private var currentPosition: Pair<Int, Int> = board.startingPoint
    var madeMistake = false
    var view : View? = null
    var previousDirection: Direction? = null
    init {
        setTile()
    }

    enum class Direction {
        LEFT, UP, RIGHT, DOWN
    }

    private fun getNextDirection(): Direction? {
        if (currentPosition.first > 0 && board.isOnTrack(Pair(currentPosition.second, currentPosition.first-1)))
            return Direction.LEFT
        if (currentPosition.first < board.size.first-1 && board.isOnTrack(Pair(currentPosition.second, currentPosition.first+1)))
            return Direction.RIGHT
        if (currentPosition.second > 0 && board.isOnTrack(Pair(currentPosition.second-1, currentPosition.first)))
            return Direction.UP
        if (currentPosition.second < board.size.second-1 && board.isOnTrack(Pair(currentPosition.second+1, currentPosition.first)))
            return Direction.DOWN
        return null // it was the last tile
    }

    // set tile on current position
    private fun setTile() {
        if (madeMistake) {
            when(previousDirection) {
                Direction.LEFT -> board.tileBoard[currentPosition.second][currentPosition.first] = Tile.LEFT_YELLOW
                Direction.UP -> board.tileBoard[currentPosition.second][currentPosition.first] = Tile.UP_YELLOW
                Direction.RIGHT -> board.tileBoard[currentPosition.second][currentPosition.first] = Tile.RIGHT_YELLOW
                Direction.DOWN -> board.tileBoard[currentPosition.second][currentPosition.first] = Tile.DOWN_YELLOW
            }
        } else {
            when(getNextDirection()) {
                Direction.LEFT -> board.tileBoard[currentPosition.second][currentPosition.first] = Tile.LEFT_GREEN
                Direction.UP -> board.tileBoard[currentPosition.second][currentPosition.first] = Tile.UP_GREEN
                Direction.RIGHT -> board.tileBoard[currentPosition.second][currentPosition.first] = Tile.RIGHT_GREEN
                Direction.DOWN -> board.tileBoard[currentPosition.second][currentPosition.first] = Tile.DOWN_GREEN
            }
        }
    }

    private fun getNextPosition(): Pair<Int, Int> {
        when (getNextDirection()) {
            Direction.LEFT -> return Pair(currentPosition.first-1, currentPosition.second)
            Direction.UP -> return Pair(currentPosition.first, currentPosition.second-1)
            Direction.RIGHT -> return Pair(currentPosition.first+1, currentPosition.second)
            Direction.DOWN -> return Pair(currentPosition.first, currentPosition.second+1)
        }
        return currentPosition // it was the last tile
    }

    // on move
    fun move(direction: Direction) {
        if (direction == getNextDirection()) {
            madeMistake = false
            setTile()
            // set tile on track to false after completing
            board.board[currentPosition.second][currentPosition.first] = false
            currentPosition = getNextPosition()
            previousDirection = null
        } else {
            if (madeMistake) gameOver()
            madeMistake = true
            when (direction) {
                Direction.LEFT -> {
                    if (currentPosition.first <= 0) gameOver()
                    previousDirection = Direction.RIGHT
                }
                Direction.UP -> {
                    if (currentPosition.second <= 0) gameOver()
                    previousDirection = Direction.DOWN
                }
                Direction.RIGHT -> {
                    if (currentPosition.first <= board.size.first) gameOver()
                    previousDirection = Direction.LEFT
                }
                Direction.DOWN -> {
                    if (currentPosition.second <= board.size.second) gameOver()
                    previousDirection = Direction.UP
                }
            }
            setTile()
        }
    }

    fun gameOver() {

    }
}