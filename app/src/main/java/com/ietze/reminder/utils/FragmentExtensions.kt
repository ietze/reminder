package com.ietze.reminder.utils

import android.app.Application
import androidx.fragment.app.Fragment

fun Fragment.requireApplication(): Application = requireActivity().application