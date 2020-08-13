package com.ietze.reminder.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ietze.reminder.data.reminder.Reminder
import com.ietze.reminder.data.reminder.ReminderDao

@Database(entities = [Reminder::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {
        fun build(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                "db.sqlite3")
                .build()
        }
    }
}

