package com.ietze.reminder.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ietze.reminder.data.reminder.Reminder
import com.ietze.reminder.data.reminder.ReminderDataSource
import com.ietze.reminder.data.Result
import kotlinx.coroutines.launch
import java.util.*

class CreateReminderViewModel(
    private val dataSource: ReminderDataSource
): ViewModel() {

    fun add(content: String, date: Date) {
        viewModelScope.launch {
            val id = dataSource.add(
                Reminder(0, content, date)
            )
            val reminder = dataSource.getById(id)
            if (reminder is Result.Success) {
                schedule(reminder.data)
            }
        }
    }

    private fun schedule(reminder: Reminder) {
        // TODO()
    }
}