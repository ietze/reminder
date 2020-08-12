package com.ietze.reminder.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ietze.reminder.data.reminder.InMemoryReminderRepository

class ReminderListViewModelFactory : ViewModelProvider.Factory {

    private var dataSource =
        InMemoryReminderRepository()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReminderListViewModel(dataSource) as T
    }
}