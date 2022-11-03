package com.example.boardgameapp.database

import com.example.boardgameapp.database.entities.User
import com.example.boardgameapp.database.user.UserDataSource

//TODO; Read further about Data and Domain Layer!
class FormatRatingUseCase(var rating: ArrayList<Double>?) {
//class FormatRatingUseCase(userId: Int) {
  //  private val user: User? = UserDataSource.users.find { it.id == userId }
    //private val rating: ArrayList<Double>? = user!!.rating

    fun getRating() : Float{
        return rating?.average()?.toFloat() ?: 0.0f
    }

}