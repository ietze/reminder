package com.ietze.reminder.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ietze.reminder.utils.di.Injector

class ReminderDetailsViewModelFactory(
    private val reminderId: Long
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReminderDetailsViewModel(reminderId, Injector.provideReminderDataSource()) as T
    }
}
