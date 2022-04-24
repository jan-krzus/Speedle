package com.example.asdf


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import hallianinc.opensource.timecounter.StopWatch
import kotlinx.android.synthetic.main.activity_game.*


private lateinit var detector: GestureDetectorCompat
private lateinit var movement: GameActivity.Movement
private lateinit var storage: ScoreStorage

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detector = GestureDetectorCompat(this, SwipeDetect())
        val mode_first  = intent.getIntExtra("mode_first", Board.Mode.NORMAL.size.first)
        val mode_second  = intent.getIntExtra("mode_second", Board.Mode.NORMAL.size.second)
        Board.size = Pair(mode_first, mode_second)
        Board.mode = if (Board.size.first == 5) Board.Mode.NORMAL else Board.Mode.HARD
        Board.generateBoard()
//        Board.fillBoard(resources)
        setContentView(R.layout.activity_game)
        movement = Movement(Board)
        storage = ScoreStorage(this)


    }
    fun dialog(view: View, time : Int, score : Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("G A M E  O V E R")
        builder.setMessage("S C O R E :  $time\n\n M Y   S C O R E: $score")

        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, id ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            })

        builder.show()
    }

    fun dialogLost(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("G A M E   O V E R")
        builder.setMessage("Y O U   L O S T")


        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, id ->
                val intent = Intent(this, MainActivity::class.java)
//                val b = Bundle()
//                b.putBoolean("new_window", true) //sets new window
//                intent.putExtras(b)
                startActivity(intent)
            })
//        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
//            Toast.makeText(applicationContext,
//                android.R.string.ok, Toast.LENGTH_SHORT).show()
//        }

        builder.show()
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        return if (detector.onTouchEvent(event)) {
            true
        }

        else{ super.onTouchEvent(event)
    }}

    enum class Direction {
        LEFT, UP, RIGHT, DOWN
    }

    inner class Movement(var board: Board) {
        private var currentPosition: Pair<Int, Int> = board.startingPoint
        var madeMistake = false
        var previousDirection: Direction? = null
        val stopwatch = StopWatch(findViewById(R.id.timer))


        init {
            setTile()
            stopwatch.start()
        }


        private fun getNextDirection(): Direction? {
//            if (! board.isOnTrack(Pair(currentPosition.first, currentPosition.second))) return null
            return Board.directionBoard[currentPosition.second][currentPosition.first]
//
//            if (currentPosition.first > 0 && board.isOnTrack(Pair(currentPosition.first-1, currentPosition.second)))
//                return Direction.LEFT
//            if (currentPosition.first < board.size.first-1 && board.isOnTrack(Pair(currentPosition.first+1, currentPosition.second)))
//                return Direction.RIGHT
//            if (currentPosition.second > 0 && board.isOnTrack(Pair(currentPosition.first, currentPosition.second-1)))
//                return Direction.UP
//            if (currentPosition.second < board.size.second-1 && board.isOnTrack(Pair(currentPosition.first, currentPosition.second+1)))
//                return Direction.DOWN
//            return null // it was the last tile
        }

        private fun getDirection(): Direction? {
            return Board.directionBoard[currentPosition.second][currentPosition.first]
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

            var nextDir = getNextDirection()
            if (nextDir == null) {
                nextDir = previousDirection
            }
            if (direction == nextDir) {
                madeMistake = false
                setTile()
                // set tile on track to false after completing
//                board.board[currentPosition.second][currentPosition.first] = false
                currentPosition = getNextPosition()
                previousDirection = null

                nextDir = getNextDirection()
            } else {
                Log.w(null, "direction != nextDir")
                stopwatch.time+=10
                if (madeMistake) youLost()
                madeMistake = true

                when (direction) {
                    Direction.LEFT -> {
                        if (currentPosition.first <= 0) youLost()
                        else {
                            currentPosition = Pair(currentPosition.first - 1, currentPosition.second)
                            previousDirection = Direction.RIGHT
                        }
                    }
                    Direction.UP -> {
                        if (currentPosition.second <= 0) youLost()
                        else {
                            currentPosition = Pair(currentPosition.first, currentPosition.second - 1)
                            previousDirection = Direction.DOWN
                        }
                    }
                    Direction.RIGHT -> {
                        if (currentPosition.first >= board.size.first) youLost()
                        else {
                        currentPosition = Pair(currentPosition.first+1, currentPosition.second)
                        previousDirection = Direction.LEFT
                        }
                    }
                    Direction.DOWN -> {
                        if (currentPosition.second >= board.size.second) youLost()
                        else {
                            currentPosition = Pair(currentPosition.first, currentPosition.second + 1)
                            previousDirection = Direction.UP
                        }
                    }
                }
            }
            setTile()
            Log.i(null, "made mistake: $madeMistake")
            Log.i(null, "position X: ${currentPosition.first}")
            Log.i(null, "position Y: ${currentPosition.second}")
            Log.i(null, "GetNextDir: ${getNextDirection()}")
            Log.i(null, "nextDir: ${nextDir}")
            Log.i(null, "previousDir: ${previousDirection}")

        }

        fun youLost(){
            stopwatch.pause()
            dialogLost(findViewById(R.id.board))
            stopwatch.stop()
        }

        fun gameOver() {
            stopwatch.pause()
            val score : Int
            if (board.size.first == 5) {
                storage.addScoreNormal(stopwatch.time)
                score = storage.getScoresNormal().second
            }else{
                storage.addScoreHard(stopwatch.time)
                score = storage.getScoresHard().second
            }
            dialog(findViewById(R.id.board), stopwatch.time, score)
            stopwatch.stop()
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