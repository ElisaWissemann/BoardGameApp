package com.example.boardgameapp.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntArrayListConverter {

    companion object{
        fun newInstance() = IntArrayListConverter
    }

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)


    @TypeConverter
    fun fromIntArrayList(value: ArrayList<Int>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toIntArrayList(value: String): ArrayList<Int> {
        return try {
            Gson().fromJson<ArrayList<Int>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }

}