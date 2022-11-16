package com.example.boardgameapp.usecases

class FormatRatingUseCase(var rating: ArrayList<Double>?) {
//class FormatRatingUseCase(userId: Int) {
  //  private val user: User? = UserDataSource.users.find { it.id == userId }
    //private val rating: ArrayList<Double>? = user!!.rating

    fun getRating() : Float{
        return rating?.average()?.toFloat() ?: 0.0f
    }

}