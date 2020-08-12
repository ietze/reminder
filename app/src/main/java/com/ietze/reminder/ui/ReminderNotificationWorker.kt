package com.ietze.reminder.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ietze.reminder.R
import com.ietze.reminder.data.reminder.ReminderDataSource
import com.ietze.reminder.utils.di.Injector

class ReminderNotificationWorker(
    private val context: Context, workerParams: WorkerParameters
): Worker(context, workerParams) {

    private val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val dataSource: ReminderDataSource = Injector.provideReminderDataSource()

    override fun doWork(): Result {

        val id = inputData.getLong("id", 0L)
        val result = dataSource.getById(id)
        when (result) {
            is com.ietze.reminder.data.Result.Success -> {
                val reminder = result.data
                var builder = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(reminder.text)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                createNotificationChannel()
                notificationManager.notify(reminder.id as Int, builder.build())
                return Result.success()
            }
        }
        return Result.failure()
    }

    private var CHANNEL_ID = "1"

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}