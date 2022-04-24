package com.example.asdf

import android.content.Context
import android.content.SharedPreferences
import kotlin.math.roundToInt

class ScoreStorage(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    private val keyAvgNormal = "SCORE_AVG_NORMAL"
    private val keyNumTriesNormal = "NUMBER_OF_TRIES_NORMAL"
    private val keyAvgHard = "SCORE_AVG_HARD"
    private val keyNumTriesHard= "NUMBER_OF_TRIES_HARD"
    private val defAvg = 0
    
    fun addScoreNormal(score: Int) {
        val currentAvg = sharedPref.getFloat(keyAvgNormal, 0F)
        val currentNumTries = sharedPref.getInt(keyNumTriesNormal, 0)

        val newNumTries = currentNumTries + 1
        val newAvg = (currentAvg * currentNumTries + score) / newNumTries
        with (sharedPref.edit()) {
            putFloat(keyAvgNormal, newAvg)
            putInt(keyNumTriesNormal, newNumTries)
            apply()
        }
    }
    
    //Int: number of tries, Int: Avg score
    fun getScoresNormal(): Pair<Int, Int> {
        return Pair(sharedPref.getInt(keyNumTriesNormal, 0), sharedPref.getFloat(keyAvgNormal, 0F).roundToInt())
    }
    
    fun addScoreHard(score: Int){
        val currentAvg = sharedPref.getFloat(keyAvgHard, 0F)
        val currentNumTries = sharedPref.getInt(keyNumTriesHard, 0)

        val newNumTries = currentNumTries + 1
        val newAvg = (currentAvg * currentNumTries + score) / newNumTries
        with (sharedPref.edit()) {
            putFloat(keyAvgHard, newAvg)
            putInt(keyNumTriesHard, newNumTries)
            apply()
        }
    }

    //Int: number of tries, Int: Avg score
    fun getScoresHard(): Pair<Int, Int> {
        return Pair(sharedPref.getInt(keyNumTriesHard, 0), sharedPref.getFloat(keyAvgHard, 0F).roundToInt())
    }
    
    fun eraseScores() {
        with (sharedPref.edit()) {
            putFloat(keyAvgNormal, 0F)
            putInt(keyNumTriesNormal, 0)
            putFloat(keyAvgHard, 0F)
            putInt(keyNumTriesHard, 0)
            apply()
        }
    }
}
