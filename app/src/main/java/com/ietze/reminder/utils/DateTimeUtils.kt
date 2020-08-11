package com.ietze.reminder.utils

import java.util.*

class DateTimeUtils {

    companion object {

        private val calendar = Calendar.getInstance(Locale("ru"))

        fun isToday(first: Date): Boolean {
            calendar.time = first
            val firstDay = calendar.get(Calendar.DAY_OF_MONTH)
            val firstMonth = calendar.get(Calendar.MONTH)
            val firstYear = calendar.get(Calendar.YEAR)

            calendar.time = Date(System.currentTimeMillis())
            val secondDay = calendar.get(Calendar.DAY_OF_MONTH)
            val secondMonth = calendar.get(Calendar.MONTH)
            val secondYear = calendar.get(Calendar.YEAR)

            return firstDay == secondDay && firstMonth == secondMonth && firstYear == secondYear
        }

        fun isYesterday(target: Date): Boolean {
            calendar.time = target
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            val targetDate = calendar.time
            return isToday(targetDate)
        }

        fun isTomorrow(target: Date): Boolean {
            calendar.time = target
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val targetDate = calendar.time
            return isToday(targetDate)
        }

        fun getTime(date: Date): String {
            calendar.time = date
            return calendar.get(Calendar.HOUR_OF_DAY).toString() + ":" + calendar.get(Calendar.MINUTE)
                .toString()
        }

        fun toDate(year: Int, month: Int, day: Int): Date {
            calendar.set(year, month, day)
            return calendar.time
        }
    }
}