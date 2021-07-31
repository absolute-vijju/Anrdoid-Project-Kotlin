package com.bodiesbyrachel.vnfitnessapp.fragments

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.DashboardActivity
import com.bodiesbyrachel.vnfitnessapp.activities.SelectedWorkoutActivity
import com.bodiesbyrachel.vnfitnessapp.activities.WorkoutTypeActivity
import com.bodiesbyrachel.vnfitnessapp.adapters.WorkoutsAdapter
import com.bodiesbyrachel.vnfitnessapp.models.WorkoutResponse
import com.bodiesbyrachel.vnfitnessapp.services.RestApiClient
import com.bodiesbyrachel.vnfitnessapp.utils.ConnectionDetector
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.GeneralFunction
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_workout.*
import kotlinx.android.synthetic.main.item_recommended_workout.*
import kotlinx.android.synthetic.main.item_workout.*
import kotlinx.android.synthetic.main.item_workout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class WorkoutFragment : Fragment(), ItemClickListener {

    private var workoutResponse: WorkoutResponse? = null
    private var recommendedWorkoutResponse:WorkoutResponse?=null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val workoutFragment = WorkoutFragment()
            workoutFragment.arguments = bundle
            return workoutFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* val connectionDetector = ConnectionDetector.isNetworkConnected(activity!!)
        if (connectionDetector) {
            val restApiClient = RestApiClient.getApiClient()
            CoroutineScope(Dispatchers.IO).launch {
                val response = restApiClient.getWorkoutData()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful)
                        Log.d("mydata", Gson().toJson(response.body()))
                    else
                        Toast.makeText(activity!!, response.code(), Toast.LENGTH_SHORT).show()
                }
            }
        } else
            ConnectionDetector.showNoInternet(activity!!)*/

        workoutResponse = Gson().fromJson(GeneralFunction.loadJSONFromAsset(activity!!, "workoutList.json"), WorkoutResponse::class.java)
        recommendedWorkoutResponse = Gson().fromJson(GeneralFunction.loadJSONFromAsset(activity!!, "recommended_workoutList.json"), WorkoutResponse::class.java)

        rvWorkouts.layoutManager = LinearLayoutManager(activity)
        val workoutsAdapter = WorkoutsAdapter(activity!!, workoutResponse!!.workoutData!!, recommendedWorkoutResponse!!.workoutData!!, this)
        rvWorkouts.adapter = workoutsAdapter


    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.ivProfilePic -> {
                (activity as DashboardActivity).replaceFragmentFromActivity(
                    ProfileFragment.newInstance(
                        null
                    )
                )
//                activity!!.startActivity(Intent(activity, QuestionnaireActivity::class.java))
            }
        }
    }


}
