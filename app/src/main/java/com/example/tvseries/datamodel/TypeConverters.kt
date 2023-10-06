package com.example.tvseries.datamodel

import androidx.room.TypeConverter

class TypeConverters {

    @TypeConverter
    fun convertFromPictures(pictures: Pictures): String {
        var result = ""

        for (pictureUrl in pictures.pictures) {
            result = if (result.isNotEmpty()) {
                "$result,$pictureUrl"

            } else {
                pictureUrl
            }
        }

        return result
    }

    @TypeConverter
    fun convertToPictures(string: String): Pictures {
        val result = ArrayList<String>()

        var pictureUrl = ""
        for (char in string.toCharArray()) {
            if (char != ',') {
                pictureUrl += char

            } else {
                result.add(pictureUrl)
                pictureUrl = ""
            }
        }

        result.add(pictureUrl)
        return Pictures(result)
    }
}