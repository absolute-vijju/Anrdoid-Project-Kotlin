package com.example.googlesignin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.googlesignin.R
import com.example.googlesignin.activities.QuestionActivity
import com.example.googlesignin.room.AppDatabase
import com.example.googlesignin.room.Dao
import com.example.googlesignin.utils.Constants
import com.example.googlesignin.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class WelcomeFragment : Fragment() {

    private var databaseDao: Dao? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val welcomeFragment = WelcomeFragment()
            welcomeFragment.arguments = bundle
            return welcomeFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseDao = AppDatabase.getDatabase(activity!!).getDao()

        GlobalScope.launch {
            val questionAnswers = databaseDao?.getQuestionAnswer()
            val stringBuilder = StringBuilder()
            if (!questionAnswers.isNullOrEmpty()) {
                for (questionAnswerIndex in questionAnswers.indices) {
                    val questionAnswer = questionAnswers[questionAnswerIndex]
                    val row =
                        "Question: ${questionAnswer.question}\nAnswer: ${questionAnswer.answer}"
                    stringBuilder.append(row)
                    if (questionAnswerIndex != questionAnswers.size - 1)
                        stringBuilder.append("\n\n")
                    withContext(Dispatchers.Main) {
                        tvData.text = stringBuilder.toString()
                    }
                }
                SharedPreference.save(
                    activity!!,
                    Constants.QUESTION_ANSWER_KEY,
                    stringBuilder.toString()
                )
                (activity as QuestionActivity).updateWidget()
            }
        }

        btnDeleteAnswer.setOnClickListener {
            SharedPreference.remove(activity!!, Constants.QUESTION_ANSWER_KEY)
            GlobalScope.launch {
                val rows = databaseDao?.getQuestionAnswer()
                if (rows != null) {
                    for (row in rows) {
                        databaseDao?.deleteQuestionAnswer(row)
                    }
                }
                withContext(Dispatchers.Main) {
                    startActivity(Intent(activity!!, QuestionActivity::class.java))
                    activity!!.finish()
                    (activity as QuestionActivity).updateWidget()
                }
            }
        }

    }

}