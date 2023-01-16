package com.example.boardgameapp.data.usecases

    class FormatRatingUseCase(var rating: ArrayList<Double>?) {
    /**Calculates the average of the given Ratings, if there are no ratings yet, 0.0f is passed*/
    fun getRating(): Float? {

        return if (!rating.isNullOrEmpty()) {
            rating?.average()?.toFloat()
        } else {
            0.0f // TODO if rating = null results in 0.0f, I dont need nullability and just could use 0.0f as default
            // TODO: Write 0 in the database as initial value
        }
    }

}