package com.example.boardgameapp.data.converters
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoubleArrayListConverter {

    companion object{
        fun newInstance() = DoubleArrayListConverter
    }

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)


    @TypeConverter
    fun fromDoubleArrayList(value: ArrayList<Double>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toDoubleArrayList(value: String): ArrayList<Double> {
        return try {
            Gson().fromJson<ArrayList<Double>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }

}