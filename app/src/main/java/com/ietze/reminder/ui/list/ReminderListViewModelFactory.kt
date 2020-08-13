package com.ietze.reminder.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ietze.reminder.data.reminder.ReminderDataSource
import javax.inject.Inject

class ReminderListViewModelFactory(
    application: android.app.Application
) : ViewModelProvider.Factory {

    @Inject
    lateinit var reminderDataSource: ReminderDataSource

    init {
        (application as com.ietze.reminder.ui.Application).appComponent.inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReminderListViewModel(reminderDataSource) as T
    }
}