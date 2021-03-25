package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture_of_day")
data class PictureOfDayEntity(
    @PrimaryKey
    val id: Int = 1, // In order to having Only 1 row
    val url: String,
    @ColumnInfo(name = "media_type") val mediaType: String,
    val title: String,
    val date: String,
    val explanation: String
)

fun PictureOfDayEntity.toModel(): PictureOfDayEntity {
    return PictureOfDayEntity(
        url = url,
        mediaType = mediaType,
        title = title,
        date = date,
        explanation = explanation
    )
}
