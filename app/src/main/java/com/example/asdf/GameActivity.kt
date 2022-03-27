package com.example.asdf

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

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
}