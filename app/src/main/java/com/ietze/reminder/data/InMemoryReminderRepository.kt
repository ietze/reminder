package com.ietze.reminder.data

import com.ietze.reminder.utils.DateTimeUtils

class InMemoryReminderRepository : ReminderDataSource {

    companion object {

        val list = mutableListOf(
            Reminder(1, "Create another reminder", DateTimeUtils.toDate(2020, 7, 10))
        )
    }

    override fun getAll(): Result<List<Reminder>> {
        return Result.Success(list)
    }

    override fun getById(id: Long): Result<Reminder> {
        return Result.Success(list.find { it.id == id } !!)
    }

    override fun add(reminder: Reminder): Long {
        list.add(Reminder(
            list.last().id,
            reminder.text,
            reminder.date
        ))
        return list.last().id
    }
}

