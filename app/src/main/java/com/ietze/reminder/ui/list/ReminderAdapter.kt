package com.ietze.reminder.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ietze.reminder.R
import com.ietze.reminder.data.reminder.Reminder
import com.ietze.reminder.utils.DateTimeUtils
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class ReminderAdapter(
    data: List<Pair<Date, List<Reminder>>>,
    private val itemClickListener: (Reminder) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewTypes = mutableListOf<Pair<Int, Int>>()
    private val dates = mutableListOf<Date>()
    private val notes = mutableListOf<Reminder>()

    init {
        data.forEach {
            dates.add(it.first)
            viewTypes.add(VIEW_TYPE_DATE to dates.lastIndex)
            it.second.forEach { note ->
                notes.add(note)
                viewTypes.add(VIEW_TYPE_REMINDER to notes.lastIndex)
            }
        }
    }

    companion object {

        private const val VIEW_TYPE_REMINDER = 1

        private const val VIEW_TYPE_DATE = 2

        private val dateTimeFormat = SimpleDateFormat("d MMMM", Locale("en-us"))
    }

    private var layoutInflater: LayoutInflater? = null

    class NoteViewHolder(root: View, val time: TextView, val text: TextView) :
        RecyclerView.ViewHolder(root)

    class DateViewHolder(root: View, val date: TextView) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        return when (viewType) {
            VIEW_TYPE_REMINDER -> {
                val view = layoutInflater!!.inflate(R.layout.item_note, parent, false)
                NoteViewHolder(
                    view,
                    view.time,
                    view.content
                )
            }
            VIEW_TYPE_DATE -> {
                val view = layoutInflater!!.inflate(R.layout.item_date, parent, false)
                DateViewHolder(
                    view,
                    view as TextView
                )
            }
            else -> throw IllegalArgumentException(viewType.toString())
        }
    }

    override fun getItemCount(): Int = viewTypes.size

    override fun getItemViewType(position: Int): Int {
        return viewTypes[position].first
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val context = holder.itemView.context

        when (holder) {
            is NoteViewHolder -> {
                val reminder = notes[viewTypes[position].second]
                holder.text.text = reminder.text
                val date = reminder.date
                holder.time.text = DateTimeUtils.getTime(date)

                holder.itemView.setOnClickListener {
                    itemClickListener(reminder)
                }
            }
            is DateViewHolder -> {
                val date = dates[viewTypes[position].second]
                when {
                    DateTimeUtils.isToday(date) -> {
                        holder.date.text = context.getString(R.string.today)
                    }
                    DateTimeUtils.isTomorrow(date) -> {
                        holder.date.text = context.getString(R.string.tomorrow)
                    }
                    DateTimeUtils.isYesterday(date) -> {
                        holder.date.text = context.getString(R.string.yesterday)
                    }
                    else -> {
                        holder.date.text = dateTimeFormat.format(date)
                    }
                }
            }
        }
    }


}
