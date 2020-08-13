package com.ietze.reminder.utils.di

import com.ietze.reminder.data.AppDatabase
import com.ietze.reminder.data.reminder.ReminderDao
import com.ietze.reminder.data.reminder.ReminderDataSource
import com.ietze.reminder.data.reminder.ReminderRepository
import dagger.Module
import dagger.Provides

@Module
class ReminderModule {

    @Provides
    fun provideReminderDataSource(dao: ReminderDao): ReminderDataSource {
        return ReminderRepository(dao)
    }

    @Provides
    fun provideReminderDao(appDatabase: AppDatabase): ReminderDao {
        return appDatabase.reminderDao()
    }
}