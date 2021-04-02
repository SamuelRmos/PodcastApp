package com.samuelrmos.podcastapp.data.room

import android.os.Build
import androidx.room.TypeConverter
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object DateTimeTypeConverters {
    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetDateTime.parse(it)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date?.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.parse(value)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalDateTime(value: LocalDateTime?): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            value?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    @TypeConverter
    @JvmStatic
    fun toDuration(value: Long?): Duration? {
        return value?.let { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Duration.ofMillis(it)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromDuration(value: Duration?): Long? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            value?.toMillis()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
}