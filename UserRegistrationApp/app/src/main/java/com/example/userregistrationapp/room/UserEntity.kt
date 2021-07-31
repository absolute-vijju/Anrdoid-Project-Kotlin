package com.example.userregistrationapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var UserId: Int,
    @ColumnInfo(name = "fullname")
    var FullName: String,
    @ColumnInfo(name = "address")
    var Address: String,
    @ColumnInfo(name = "email")
    var Email: String,
    @ColumnInfo(name = "city")
    var City: String,
    @ColumnInfo(name = "state")
    var State: String,
    @ColumnInfo(name = "country")
    var Country: String,
    @ColumnInfo(name = "zip_code")
    var ZipCode: String,
    @ColumnInfo(name = "phone_number")
    var PhoneNumber: String,
    @ColumnInfo(name = "mobile")
    var Mobile: String
)