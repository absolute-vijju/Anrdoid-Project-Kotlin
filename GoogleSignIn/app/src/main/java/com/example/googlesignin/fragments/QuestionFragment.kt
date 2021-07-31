package com.example.googlesignin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googlesignin.R
import com.example.googlesignin.activities.QuestionActivity
import com.example.googlesignin.adapters.QuestionAdapter
import com.example.googlesignin.models.QuestionsResponse
import com.example.googlesignin.room.AppDatabase
import com.example.googlesignin.room.Dao
import com.example.googlesignin.room.QuestionEntity
import com.example.googlesignin.utils.GeneralFunctions
import com.example.googlesignin.utils.ItemClickListener
import com.example.googlesignin.utils.OptionChangeListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.btn_continue.view.*
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuestionFragment : Fragment(), ItemClickListener, OptionChangeListener {

    private var currentQuestion = 0
    private var totalQuestion: Int? = null
    private var selectedOptions = hashMapOf<Int, Int>()
    private var databaseDao: Dao? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val questionFragment = QuestionFragment()
            questionFragment.arguments = bundle
            return questionFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        val path = activity!!.getDatabasePath("person_db").absolutePath

    }

    private fun init() {
        databaseDao = AppDatabase.getDatabase(activity!!).getDao()
        rvQuestions.layoutManager = LinearLayoutManager(activity!!)
        getData()
    }

    private fun getData() {
        (activity as QuestionActivity).questionsResponse = Gson().fromJson(
            GeneralFunctions.loadJSONFromAsset(activity!!, "questions_response.json"),
            QuestionsResponse::class.java
        )
        setAdapter()
    }

    private fun setAdapter() {
        if ((activity as QuestionActivity).questionsResponse != null) {
            totalQuestion = (activity as QuestionActivity).questionsResponse!!.questionData!!.size

            val questionsAdapter =
                QuestionAdapter(
                    activity!!,
                    (activity as QuestionActivity).questionsResponse!!.questionData!!,
                    currentQuestion,
                    this,
                    this
                )
            rvQuestions.adapter = questionsAdapter
        }
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.btnContinue -> {
                if (viewHolder.itemView.btnContinue.text == getString(R.string._continue)) {
                    if (currentQuestion != totalQuestion!! - 1) {
                        currentQuestion++
                        setAdapter()
                    }
                } else {
                    val sortedMap  = selectedOptions.toSortedMap()
                    GlobalScope.launch {
                        for (option in sortedMap) {
                            databaseDao?.insertQuestionAnswer(
                                QuestionEntity(
                                    question = (activity as QuestionActivity).questionsResponse!!.questionData!![option.key].question!!,
                                    answer = (activity as QuestionActivity).questionsResponse!!.questionData!![option.key].optionData!![option.value].option!!
                                )
                            )
                        }
                        withContext(Dispatchers.Main) {
                            (activity as QuestionActivity).replaceFragmentFromActivity(
                                WelcomeFragment.newInstance(null)
                            )
                        }

                    }
                    (activity as QuestionActivity).updateWidget()
                }
            }
        }
    }

    override fun onOptionChange(position: Int) {
            selectedOptions[currentQuestion] = position
    }

}