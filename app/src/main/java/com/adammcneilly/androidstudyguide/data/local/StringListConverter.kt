package com.adammcneilly.androidstudyguide.data.local

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(DELIMITER)
    }

    @TypeConverter
    fun stringListToString(value: List<String>): String {
        return value.joinToString(DELIMITER)
    }

    companion object {
        private const val DELIMITER = ","
    }
}
