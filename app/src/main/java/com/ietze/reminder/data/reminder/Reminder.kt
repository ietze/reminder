package com.ietze.reminder.data.reminder

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ietze.reminder.data.DateConverters
import java.util.*

@Entity
@TypeConverters(DateConverters::class)
class Reminder(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val text: String,

    val date: Date
)