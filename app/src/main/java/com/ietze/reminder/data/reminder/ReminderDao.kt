package com.ietze.reminder.data.reminder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReminderDao {

    @Query("SELECT * FROM Reminder")
    suspend fun getAll(): List<Reminder>

    @Query("SELECT * FROM Reminder WHERE id == :id")
    suspend fun getById(id: Long): Reminder?

    @Insert
    suspend fun insert(reminder: Reminder): Long

    @Query("DELETE FROM Reminder WHERE id == :id")
    suspend fun delete(id: Long)
}