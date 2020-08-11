package com.ietze.reminder.ui.create

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ietze.reminder.R
import kotlinx.android.synthetic.main.fragment_create_reminder.view.*

class CreateReminderFragment : Fragment() {

    private lateinit var viewModel: CreateReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_reminder, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, CreateReminderViewModelFactory(requireActivity().application))
            .get(CreateReminderViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.create_reminder.setOnClickListener {
            val content = view.content.text.toString()
            val date = view.date_time_picker.date

            if (content.isEmpty()) {
                showAnimation(view.content)
                return@setOnClickListener
            }

            viewModel.add(content, date)
            parentFragmentManager.popBackStack()
        }
    }

    private fun showAnimation(editText: EditText) {
        val drawable = editText.background as GradientDrawable
        drawable.setStroke(10, Color.BLACK)
    }
}