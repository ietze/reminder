package com.ietze.reminder.data

import androidx.room.TypeConverter
import java.util.*

class DateConverters {

    @TypeConverter
    fun toDatabaseFormat(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromDatabaseFormat(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }
}