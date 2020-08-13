package com.ietze.reminder.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ietze.reminder.data.reminder.ReminderDataSource
import javax.inject.Inject

class ReminderDetailsViewModelFactory(
    private val reminderId: Long,
    application: android.app.Application
) : ViewModelProvider.Factory {

    @Inject
    lateinit var reminderDataSource: ReminderDataSource

    init {
        (application as com.ietze.reminder.ui.Application).appComponent.inject(this)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReminderDetailsViewModel(reminderId, reminderDataSource) as T
    }
}
