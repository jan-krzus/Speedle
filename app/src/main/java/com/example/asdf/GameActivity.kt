package com.example.asdf


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GestureDetectorCompat

private lateinit var detector: GestureDetectorCompat
private lateinit var movement: Movement

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        detector = GestureDetectorCompat(this, SwipeDetect())
        movement = Movement(Board(Pair(6,5), Pair(0,0)))
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

    inner class SwipeDetect : GestureDetector.SimpleOnGestureListener() {

        private val  SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

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
        Toast.makeText(this, "DOWN",Toast.LENGTH_LONG)
//        movement.move(Movement.Direction.DOWN)
    }

    private fun onSwipeUp(){
        Toast.makeText(this, "UP",Toast.LENGTH_LONG)
//        movement.move(Movement.Direction.UP)
    }

    internal fun onSwipeLeft(){
        Toast.makeText(this, "LEFT",Toast.LENGTH_LONG)
//        movement.move(Movement.Direction.LEFT)
    }

    internal fun onSwipeRight(){
        Toast.makeText(this, "RIGHT",Toast.LENGTH_LONG)
//        movement.move(Movement.Direction.RIGHT)
    }


}