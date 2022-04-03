package com.example.asdf


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GestureDetectorCompat
import kotlin.math.log
//
private lateinit var detector: GestureDetectorCompat
private lateinit var movement: GameActivity.Movement

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        detector = GestureDetectorCompat(this, SwipeDetect())
        Board.size = Pair(5,6)
        Board.startingPoint = Pair(0,0)
        movement = Movement(Board)


    }
    fun dialog(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("G A M E  O V E R")
        builder.setMessage("S C O R E :  " + 10)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    enum class Direction {
        LEFT, UP, RIGHT, DOWN
    }

    inner class Movement(var board: Board) {
        private var currentPosition: Pair<Int, Int> = board.startingPoint
        var madeMistake = false
        var view : View? = null
        var previousDirection: Direction? = null
        init {
            setTile()
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
            dialog(findViewById(R.id.board))
        }
    }

    inner class SwipeDetect : GestureDetector.SimpleOnGestureListener() {

        private val  SWIPE_THRESHOLD = 10
        private val SWIPE_VELOCITY_THRESHOLD = 10

        override fun onFling(
            downEvent: MotionEvent?,
            moveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            return if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0 ) {
                        this@GameActivity.onSwipeRight()
                    } else {
                        this@GameActivity.onSwipeLeft()
                    }
                    true
                } else  {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            } else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        this@GameActivity.onSwipeDown()
                    } else {
                        this@GameActivity.onSwipeUp()
                    }
                    true
                } else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }


        }
    }
    private fun onSwipeDown(){
        val viewBoard : View = findViewById(R.id.board)
        viewBoard.invalidate()
        movement.move(Direction.DOWN)
    }

    private fun onSwipeUp(){
        val viewBoard : View = findViewById(R.id.board)
        viewBoard.invalidate()
        movement.move(Direction.UP)
    }

    internal fun onSwipeLeft(){
        val viewBoard : View = findViewById(R.id.board)
        viewBoard.invalidate()
        movement.move(Direction.LEFT)
    }

    internal fun onSwipeRight(){
        val viewBoard : View = findViewById(R.id.board)
        viewBoard.invalidate()
        movement.move(Direction.RIGHT)
    }


}