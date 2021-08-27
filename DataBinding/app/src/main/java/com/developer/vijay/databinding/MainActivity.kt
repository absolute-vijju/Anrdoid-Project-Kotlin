package com.developer.vijay.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.vijay.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val userAdapter by lazy {
        UserAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.title = myTitle()

        mBinding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        userAdapter.setData(getUserData())
    }

    private fun myTitle(): String = "User List"

    private fun getUserData(): ArrayList<User> {
        val userList = arrayListOf<User>()
        userList.add(User("Vijay", "Koshti"))
        userList.add(User("Dileep", "Patel"))
        userList.add(User("Jignesh", "Rajora"))
        userList.add(User("Vijay", "Koshti"))
        userList.add(User("Dileep", "Patel"))
        userList.add(User("Jignesh", "Rajora"))
        userList.add(User("Vijay", "Koshti"))
        userList.add(User("Dileep", "Patel"))
        userList.add(User("Jignesh", "Rajora"))
        userList.add(User("Vijay", "Koshti"))
        userList.add(User("Dileep", "Patel"))
        userList.add(User("Jignesh", "Rajora"))
        userList.add(User("Vijay", "Koshti"))
        userList.add(User("Dileep", "Patel"))
        userList.add(User("Jignesh", "Rajora"))
        return userList
    }
}