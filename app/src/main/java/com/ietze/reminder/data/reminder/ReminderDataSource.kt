package com.ietze.reminder.data.reminder

import com.ietze.reminder.data.Result

interface ReminderDataSource {

    fun getAll(): Result<List<Reminder>>

    fun getById(id: Long): Result<Reminder>

    fun add(reminder: Reminder): Long
}