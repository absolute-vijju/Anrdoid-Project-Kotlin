package com.example.googlesignin.activities

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.googlesignin.R
import com.example.googlesignin.fragments.QuestionFragment
import com.example.googlesignin.fragments.WelcomeFragment
import com.example.googlesignin.models.QuestionsResponse
import com.example.googlesignin.utils.Constants
import com.example.googlesignin.utils.FragmentUtils
import com.example.googlesignin.utils.MyAppWidgetProvider
import com.example.googlesignin.utils.SharedPreference


class QuestionActivity : AppCompatActivity() {

    var questionsResponse: QuestionsResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val data = SharedPreference.getString(this@QuestionActivity, Constants.QUESTION_ANSWER_KEY, "")
        if (data.isBlank() || data.isEmpty())
            addFragmentFromActivity(QuestionFragment.newInstance(null))
        else
            addFragmentFromActivity(WelcomeFragment.newInstance(null))

    }

    fun updateWidget() {
        val intent = Intent(this, MyAppWidgetProvider::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        val ids: IntArray = AppWidgetManager.getInstance(application)
            .getAppWidgetIds(ComponentName(application, MyAppWidgetProvider::class.java))
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        sendBroadcast(intent)
    }

    private fun addFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.addFragment(supportFragmentManager, R.id.flQuestion, fragment)
    }

    fun replaceFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.replaceFragment(supportFragmentManager, R.id.flQuestion, fragment)
    }

}