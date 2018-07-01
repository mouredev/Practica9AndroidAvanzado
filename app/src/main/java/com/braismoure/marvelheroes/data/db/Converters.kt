package com.braismoure.marvelheroes.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by MoureDev by Brais Moure on 1/7/18.
 * www.mouredev.com
 */
class Converters {

    @TypeConverter
    fun fromString(value: String): Array<String> {
        val arrayType = object : TypeToken<Array<String>>() {

        }.getType()
        return Gson().fromJson(value, arrayType)
    }

    @TypeConverter
    fun fromArray(array: Array<String>): String {
        val gson = Gson()
        return gson.toJson(array)
    }
}