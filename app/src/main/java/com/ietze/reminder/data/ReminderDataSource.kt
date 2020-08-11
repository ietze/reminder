package com.ietze.reminder.data

interface ReminderDataSource {

    fun getAll(): Result<List<Reminder>>

    fun getById(id: Long): Result<Reminder>

    fun add(reminder: Reminder): Long
}