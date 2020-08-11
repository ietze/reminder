package com.ietze.reminder.ui.create

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ietze.reminder.utils.di.Injector

class CreateReminderViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateReminderViewModel(application, Injector.provideReminderDataSource()) as T
    }
}