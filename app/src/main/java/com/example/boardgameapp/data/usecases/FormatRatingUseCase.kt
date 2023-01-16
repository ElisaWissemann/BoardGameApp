package com.example.boardgameapp.data.usecases

import android.util.Log

class FormatRatingUseCase(var rating: ArrayList<Double>?) {
    /**Calculates the average of the given Ratings, if there are no ratings yet, 0.0f is passed*/
    fun getRating(): Float? {

        return if (!rating.isNullOrEmpty()) {
            Log.i("ELisa", rating.toString())
            rating?.average()?.toFloat()
        } else {
            0.0f
        }
    }

}