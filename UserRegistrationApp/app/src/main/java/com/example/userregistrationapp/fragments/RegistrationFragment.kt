package com.example.userregistrationapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import com.example.userregistrationapp.R
import com.example.userregistrationapp.room.AppDatabase
import com.example.userregistrationapp.room.UserDao
import com.example.userregistrationapp.room.UserEntity
import com.example.userregistrationapp.utils.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.FieldPosition

class RegistrationFragment : Fragment(), View.OnClickListener {

    private var cityArray = arrayOf<String>()
    private var stateArray = arrayOf<String>()
    private var countryArray = arrayOf<String>()
    private var userDao: UserDao? = null
    private var city: String? = null
    private var state: String? = null
    private var country: String? = null
    private var flowType: String? = null
    private var userEntity: UserEntity? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val registrationFragment = RegistrationFragment()
            registrationFragment.arguments = bundle
            return registrationFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {

        activity?.let {
            userDao = AppDatabase.getDatabase(it).getDao()
            cityArray = it.resources.getStringArray(R.array.city_array)
            stateArray = it.resources.getStringArray(R.array.state_array)
            countryArray = it.resources.getStringArray(R.array.country_array)

            flowType = arguments?.getString(Constants.FLOW_TYPE)
            Log.d("mydata", flowType.toString())
            userEntity = Gson().fromJson(
                arguments?.getString(Constants.SELECTED_USER),
                UserEntity::class.java
            )

            if (flowType.equals(Constants.SAVE, true))
                btnSave.text = it.getString(R.string.save)
            else {
                setData()
                btnSave.text = it.getString(R.string.update)
            }

        }
        listeners()
    }

    private fun setData() {
        userEntity?.let {
            for (i in cityArray.indices) {
                if (it.City == cityArray[i])
                    spCity.setSelection(i)
            }
            for (i in stateArray.indices) {
                if (it.State == stateArray[i])
                    spState.setSelection(i)
            }
            for (i in countryArray.indices) {
                if (it.Country == countryArray[i])
                    spCountry.setSelection(i)
            }
            etFullname.setText(it.FullName)
            etAddress.setText(it.Address)
            etEmail.setText(it.Email)
            etZipCode.setText(it.ZipCode)
            etPhoneNumber.setText(it.PhoneNumber)
            etMobile.setText(it.Mobile)
        }
    }

    private fun listeners() {
        btnSave.setOnClickListener(this)
        spCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.d("SpinnerData", cityArray[position])
                city = cityArray[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
        spState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.d("SpinnerData", stateArray[position])
                state = stateArray[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
        spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.d("SpinnerData", countryArray[position])
                country = countryArray[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
    }

    private fun manageUser() {
        activity?.let {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d(
                    "mydata",
                    "${etFullname.text}\n ${etAddress.text}\n ${etEmail.text}\n $city \n $state \n $country \n ${etZipCode.text} \n ${etPhoneNumber.text} \n ${etMobile.text} }"
                )
                if (flowType.equals(Constants.SAVE, true)) {
                    val userEntity = UserEntity(0,
                        etFullname.text.toString(),
                        etAddress.text.toString(),
                        etEmail.text.toString(),
                        city!!,
                        state!!,
                        country!!,
                        etZipCode.text.toString(),
                        etPhoneNumber.text.toString(),
                        etMobile.text.toString()
                    )
                    userDao?.insertUser(userEntity)
                }
                else {
                    val userEntity = UserEntity(
                        userEntity!!.UserId,
                        etFullname.text.toString(),
                        etAddress.text.toString(),
                        etEmail.text.toString(),
                        city!!,
                        state!!,
                        country!!,
                        etZipCode.text.toString(),
                        etPhoneNumber.text.toString(),
                        etMobile.text.toString()
                    )
                    userDao?.updateUser(userEntity)
                }
            }
            if (flowType.equals(Constants.SAVE, true))
                Toast.makeText(it, it.getString(R.string.user_created), Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(it, it.getString(R.string.user_updated), Toast.LENGTH_SHORT).show()
            it.supportFragmentManager.popBackStack()
        }

    }

    private fun validateUserInput(): Boolean {
        if (etFullname.text.toString().isBlank()) {
            tilFullname.error = activity?.getString(R.string.this_field_is_mandatory)
            return false
        } else
            tilFullname.isErrorEnabled = false

        if (etAddress.text.toString().isBlank()) {
            tilAddress.error = activity?.getString(R.string.this_field_is_mandatory)
            return false
        } else
            tilAddress.isErrorEnabled = false

        if (etEmail.text.toString().isBlank()) {
            tilEmail.error = activity?.getString(R.string.this_field_is_mandatory)
            return false
        } else
            tilEmail.isErrorEnabled = false

        if (etZipCode.text.toString().isBlank()) {
            tilZipCode.error = activity?.getString(R.string.this_field_is_mandatory)
            return false
        } else
            tilZipCode.isErrorEnabled = false

        if (etPhoneNumber.text.toString().isBlank()) {
            tilPhoneNumber.error = activity?.getString(R.string.this_field_is_mandatory)
            return false
        } else {
            if (etPhoneNumber.text?.length!! < 10) {
                activity?.let {
                    Toast.makeText(
                        activity!!,
                        it.getString(R.string.please_enter_valid_phone_number),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return false
            }
            tilPhoneNumber.isErrorEnabled = false
        }

        if (etMobile.text.toString().isBlank()) {
            tilMobile.error = activity?.getString(R.string.this_field_is_mandatory)
            return false
        } else {
            if (etMobile.text?.length!! < 10) {
                activity?.let {
                    Toast.makeText(
                        activity!!,
                        it.getString(R.string.please_enter_valid_mobile_number),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return false
            }
            tilMobile.isErrorEnabled = false
        }

        return true
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSave -> {
                if (validateUserInput())
                    manageUser()
            }
        }
    }

}