package com.ietze.reminder.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ietze.reminder.data.reminder.Reminder
import com.ietze.reminder.data.reminder.ReminderDataSource
import kotlinx.coroutines.launch
import com.ietze.reminder.data.Result

class ReminderListViewModel(
    private val dataSource: ReminderDataSource
) : ViewModel() {

    private val _reminders = MutableLiveData<List<Reminder>>()
    val reminders: LiveData<List<Reminder>> = _reminders

    fun loadAll() {
        viewModelScope.launch {
            val result = dataSource.getAll()
            when (result) {
                is Result.Success -> _reminders.postValue(result.data)
            }
        }
    }
}