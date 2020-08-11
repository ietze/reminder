package com.ietze.reminder.utils.di

import com.ietze.reminder.data.ReminderDataSource
import com.ietze.reminder.data.InMemoryReminderRepository

class Injector {

    companion object {

        private val repository = InMemoryReminderRepository()

        fun provideReminderDataSource(): ReminderDataSource {
            return repository
        }
    }
}