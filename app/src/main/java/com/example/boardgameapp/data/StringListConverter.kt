package com.example.boardgameapp.data

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class StringListConverter {


    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    @TypeConverter
    fun fromStringList(value: MutableList<String>): String{
        return Gson().toJson(value)
    }

    @TypeConverters
    fun toStringList(value: String): MutableList<String>{
        return try {
            Gson().fromJson<MutableList<String>>(value)
        } catch (e: Exception){
            Log.i("ELISA", "error on converting toStringList")
            mutableListOf<String>()
        }
    }


}