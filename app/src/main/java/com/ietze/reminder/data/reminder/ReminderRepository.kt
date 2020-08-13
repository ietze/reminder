package com.ietze.reminder.data.reminder

import com.ietze.reminder.data.Error
import com.ietze.reminder.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReminderRepository(
    private val dao: ReminderDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ReminderDataSource {

    override suspend fun getAll(): Result<List<Reminder>> {
        return withContext(ioDispatcher) {
            return@withContext Result.Success(dao.getAll())
        }
    }

    override suspend fun getById(id: Long): Result<Reminder> {
        return withContext(ioDispatcher) {
            val reminder = dao.getById(id)
            when (reminder == null) {
                false -> Result.Success(reminder)
                true -> Result.Fail(Error.NotExist)
            }
        }
    }

    override suspend fun add(reminder: Reminder): Long {
        return withContext(ioDispatcher) {
            dao.insert(reminder)
        }
    }

    override suspend fun delete(id: Long) {
        return withContext(ioDispatcher) {
            dao.delete(id)
        }
    }
}

