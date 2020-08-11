package com.ietze.reminder.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.ietze.reminder.R
import com.ietze.reminder.utils.DateTimeUtils
import kotlinx.android.synthetic.main.reminder_details_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

class ReminderDetailsFragment : Fragment() {

    companion object {
        fun newInstance(reminderId: Long) = ReminderDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_REMINDER_ID, reminderId)
            }
        }

        private const val ARG_REMINDER_ID = "id"
    }

    private lateinit var viewModel: ReminderDetailsViewModel

    private val dateFormat = SimpleDateFormat("d MMMM", Locale("en-us"))
    private val timeFormat = SimpleDateFormat("hh:mm", Locale("en-us"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ReminderDetailsViewModelFactory(
            requireArguments().getLong(ARG_REMINDER_ID)
        )
        viewModel = ViewModelProvider(this, factory).get(ReminderDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reminder_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        viewModel.reminder.observe(viewLifecycleOwner, Observer {
            view.title.text = it.text

            view.date.text = when {
                DateTimeUtils.isToday(it.date) -> {
                    view.context.getString(R.string.today)
                }
                DateTimeUtils.isTomorrow(it.date) -> {
                    view.context.getString(R.string.tomorrow)
                }
                DateTimeUtils.isYesterday(it.date) -> {
                    view.context.getString(R.string.yesterday)
                }
                else -> {
                    dateFormat.format(it.date)
                }
            }

            view.date.text = view.date.text.toString() + " " + timeFormat.format(it.date)
        })

        viewModel.loadReminder()
    }
}
