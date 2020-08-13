package com.ietze.reminder.data.reminder

import com.ietze.reminder.data.Result

interface ReminderDataSource {

    suspend fun getAll(): Result<List<Reminder>>

    suspend fun getById(id: Long): Result<Reminder>

    suspend fun add(reminder: Reminder): Long

    suspend fun delete(id: Long)
}