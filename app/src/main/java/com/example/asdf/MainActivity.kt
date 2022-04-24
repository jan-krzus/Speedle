package com.example.asdf

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import kotlinx.android.parcel.Parcelize

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val start_BTN = findViewById(R.id.button10) as Button
        start_BTN.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("mode_first", Board.Mode.NORMAL.size.first)
            intent.putExtra("mode_second", Board.Mode.NORMAL.size.second)
            startActivity(intent)
        }

        val start_hard_BTN = findViewById(R.id.button11) as Button
        start_hard_BTN.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("mode_first", Board.Mode.HARD.size.first)
            intent.putExtra("mode_second", Board.Mode.HARD.size.second)

            startActivity(intent)

        }

        val about_BTN = findViewById(R.id.button12) as Button
        about_BTN.setOnClickListener {
            val storage = ScoreStorage(this)
            val triesNormal = storage.getScoresNormal().first
            val avgNormal = storage.getScoresNormal().second
            val triesHard= storage.getScoresNormal().first
            val avgHard = storage.getScoresNormal().second
            val builder = AlertDialog.Builder(this)
            builder.setTitle("A B O U T")
            builder.setMessage("M Y   S C O R E : \n\n" +
                    "   N O R M A L :   A V G :  $avgNormal\n" +
                    "   N O R M A L :   T R I E S :  $triesNormal\n\n" +
                    "   H A R D :   A V G :  $avgHard\n" +
                    "   H A R D :   T R I E S :  $triesHard\n\n\nA U T H O R S : \n\n   J A N   K R Z U S\n   W O J C I E C H   K O P A N S K I")

            builder.show()
        }

        val reset_BTN = findViewById(R.id.button13) as Button
        reset_BTN.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Y O U   S U R E ?")
            builder.setNegativeButton("N O", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
            builder.setPositiveButton("Y E S",
                DialogInterface.OnClickListener { dialog, id ->
                    val storage = ScoreStorage(this)
                    storage.eraseScores()
                })


            builder.show()
        }

    }

}

