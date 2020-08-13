package com.ietze.reminder.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ietze.reminder.R
import com.ietze.reminder.data.reminder.Reminder
import com.ietze.reminder.ui.create.CreateReminderFragment
import com.ietze.reminder.ui.details.ReminderDetailsFragment
import com.ietze.reminder.utils.requireApplication
import kotlinx.android.synthetic.main.fragment_reminder_list.view.*
import java.util.*

class ReminderListFragment: Fragment() {

    private lateinit var viewModel: ReminderListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ReminderListViewModelFactory(requireApplication()))
            .get(ReminderListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reminder_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.create_reminder.setOnClickListener {
            createReminder()
        }

        view.list.layoutManager = LinearLayoutManager(view.context)

        viewModel.reminders.observe(viewLifecycleOwner, Observer { list ->
            view.list.adapter = ReminderAdapter(process(list)) {
                navigateToReminder(it.id)
            }
        })

        viewModel.loadAll()
    }

    private fun process(list: List<Reminder>): List<Pair<Date, List<Reminder>>> {
        return list.groupBy { it.date.removeTime() }.toList().sortedBy { it.first }
    }

    private fun createReminder() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, CreateReminderFragment(), "reminder_create")
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToReminder(id: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ReminderDetailsFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }
}

private val calendar = Calendar.getInstance()
private fun Date.removeTime(): Date {
    calendar.time = this
    calendar.clear(Calendar.HOUR)
    calendar.clear(Calendar.HOUR_OF_DAY)
    calendar.clear(Calendar.MINUTE)
    calendar.clear(Calendar.SECOND)
    calendar.clear(Calendar.MILLISECOND)
    return calendar.time
}