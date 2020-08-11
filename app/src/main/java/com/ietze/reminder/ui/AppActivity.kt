package com.ietze.reminder.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.ietze.reminder.R
import com.ietze.reminder.ui.list.ReminderListFragment

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        applyFullscreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<FrameLayout>(R.id.container)
        applyInsets(container)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container,
                ReminderListFragment(), "reminder_list")
            .commit()
    }

    private fun applyFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    decorView.systemUiVisibility = decorView.systemUiVisibility or
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    decorView.systemUiVisibility = decorView.systemUiVisibility or
                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }

                statusBarColor = ContextCompat.getColor(context, android.R.color.transparent)
                navigationBarColor = ContextCompat.getColor(context, android.R.color.transparent)
            }
        }
    }

    private fun applyInsets(view: View) {
        val paddingTop = view.paddingTop
        val paddingBottom = view.paddingBottom
        view.setOnApplyWindowInsetsListener { _, insets ->
            view.updatePadding(
                    top = paddingTop + insets.systemWindowInsetTop,
                    bottom = paddingBottom + insets.systemWindowInsetBottom
            )
            insets.consumeSystemWindowInsets()
        }
    }
}
