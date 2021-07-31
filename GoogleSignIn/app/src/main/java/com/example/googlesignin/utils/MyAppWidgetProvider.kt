package com.example.googlesignin.utils

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.googlesignin.R
import com.example.googlesignin.activities.QuestionActivity

class MyAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        if (appWidgetIds != null) {
            for (appWidgetId in appWidgetIds) {
                val intent = Intent(context, QuestionActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(context, 101, intent, 0)

                val remoteViews = RemoteViews(context?.packageName, R.layout.item_widget)
                remoteViews.setOnClickPendingIntent(R.id.tvQuestionFilledStatus, pendingIntent)
                val data = SharedPreference.getString(context!!, Constants.QUESTION_ANSWER_KEY, context.getString(R.string.fill_questionnaire))
                remoteViews.setCharSequence(R.id.tvQuestionFilledStatus, "setText", data)
                appWidgetManager?.updateAppWidget(appWidgetId, remoteViews)
            }

        }

    }
}