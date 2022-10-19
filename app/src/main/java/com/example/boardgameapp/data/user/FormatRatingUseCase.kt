package com.example.boardgameapp.data.user

//TODO; Read further about Data and Domain Layer!
class FormatRatingUseCase(userId: Int) {
    private val user: User? = UserDataSource.users.find { it.id == userId }
    private val rating: List<Double> = user!!.rating

    fun getRating() : Float{
        return rating.average().toFloat()
    }

}