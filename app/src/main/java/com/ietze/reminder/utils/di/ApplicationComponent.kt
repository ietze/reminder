package com.ietze.reminder.utils.di

import com.ietze.reminder.ui.create.CreateReminderViewModelFactory
import com.ietze.reminder.ui.details.ReminderDetailsViewModelFactory
import com.ietze.reminder.ui.list.ReminderListViewModelFactory
import dagger.Component

@Component(modules = [AppModule::class, ReminderModule::class])
interface ApplicationComponent {

    fun inject(factory: ReminderDetailsViewModelFactory)

    fun inject(factory: ReminderListViewModelFactory)

    fun inject(factory: CreateReminderViewModelFactory)

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        fun appModule(appModule: AppModule): Builder
    }
}