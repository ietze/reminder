package com.ietze.reminder.ui.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ietze.reminder.data.Reminder
import com.ietze.reminder.data.ReminderDataSource
import com.ietze.reminder.data.Result
import kotlinx.coroutines.launch
import java.util.*

class CreateReminderViewModel(
    application: Application,
    private val dataSource: ReminderDataSource
): AndroidViewModel(application) {

    fun add(content: String, date: Date) {
        viewModelScope.launch {
            val id = dataSource.add(Reminder(0, content, date))
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