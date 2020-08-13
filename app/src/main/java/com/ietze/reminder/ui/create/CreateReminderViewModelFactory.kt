package com.ietze.reminder.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ietze.reminder.data.reminder.ReminderDataSource
import javax.inject.Inject

class CreateReminderViewModelFactory(
    private val application: android.app.Application
) : ViewModelProvider.Factory {

    @Inject
    lateinit var reminderDataSource: ReminderDataSource

    init {
        (application as com.ietze.reminder.ui.Application).appComponent.inject(this)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateReminderViewModel(reminderDataSource) as T
    }
}