package com.example.userregistrationapp.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userregistrationapp.R
import com.example.userregistrationapp.adapters.UserAdapter
import com.example.userregistrationapp.room.AppDatabase
import com.example.userregistrationapp.room.UserDao
import com.example.userregistrationapp.room.UserEntity
import com.example.userregistrationapp.utils.Constants
import com.example.userregistrationapp.utils.FragmentUtils
import com.example.userregistrationapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UsersFragment : Fragment(), View.OnClickListener, ItemClickListener {

    private var userDao: UserDao? = null
    private var userList: List<UserEntity>? = null
    private var userAdapter: UserAdapter? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val usersFragment = UsersFragment()
            usersFragment.arguments = bundle
            return usersFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            userList = userDao?.getUsers()
            Log.d("mydata", Gson().toJson(userList))
            withContext(Dispatchers.Main) {
                setAdapter()
            }

        }
    }

    private fun init() {
        activity?.let {
            rvUsers.layoutManager = LinearLayoutManager(it)
            userDao = AppDatabase.getDatabase(it).getDao()
        }
        getData()
        listeners()
    }

    private fun listeners() {
        fabAddUser.setOnClickListener(this)
    }

    private fun showDialog(position: Int) {
        val alertDialog =
            AlertDialog.Builder(activity!!).setTitle("Are you sure want to delete?")
                .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        CoroutineScope(Dispatchers.IO).launch {
                            if (userDao != null && !userList.isNullOrEmpty())
                                userDao?.deleteUser(userList!![position])
                            userList = userDao?.getUsers()

                            withContext(Dispatchers.Main) {
                                validateData()
                                userList?.let { userAdapter?.updateAdapter(it) }
                            }
                        }
                    }
                })
                .setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })
                .setCancelable(false)
                .show()
    }

    private fun validateData() {
        if (userList.isNullOrEmpty()) {
            tvNoDataFound.visibility = View.VISIBLE
            rvUsers.visibility = View.GONE
        } else {
            tvNoDataFound.visibility = View.GONE
            rvUsers.visibility = View.VISIBLE
        }
    }

    private fun setAdapter() {
        activity?.let {
            validateData()
            userAdapter = UserAdapter(it, userList!!, this)
            rvUsers.adapter = userAdapter
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fabAddUser -> {
                activity?.let {
                    val bundle = Bundle()
                    bundle.putString(Constants.FLOW_TYPE, Constants.SAVE)
                    FragmentUtils.replaceFragmentWithBackStack(
                        it.supportFragmentManager,
                        R.id.flMainActivityContainer,
                        RegistrationFragment.newInstance(bundle)
                    )
                }
            }
        }
    }

    override fun onItemClick(viwHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        if (viewId == R.id.ivDelete) {
            showDialog(position)
        } else
            activity?.let {
                val bundle = Bundle()
                bundle.putString(Constants.FLOW_TYPE, Constants.UPDATE)
                bundle.putString(Constants.SELECTED_USER, Gson().toJson(userList!![position]))
                FragmentUtils.replaceFragmentWithBackStack(
                    it.supportFragmentManager,
                    R.id.flMainActivityContainer,
                    RegistrationFragment.newInstance(bundle)
                )
            }
    }
}