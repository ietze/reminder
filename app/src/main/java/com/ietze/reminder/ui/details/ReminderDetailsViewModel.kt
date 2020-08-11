package com.ietze.reminder.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ietze.reminder.data.Reminder
import com.ietze.reminder.data.ReminderDataSource
import com.ietze.reminder.data.Result
import kotlinx.coroutines.launch

class ReminderDetailsViewModel(
    private val reminderId: Long,
    private val dataSource: ReminderDataSource
) : ViewModel() {

    private val _reminder = MutableLiveData<Reminder>()
    val reminder: LiveData<Reminder> = _reminder

    fun loadReminder() {
        viewModelScope.launch {
            val result = dataSource.getById(reminderId)
            when (result) {
                is Result.Success -> _reminder.postValue(result.data)
            }
        }
    }
}
