package com.example.userregistrationapp.room

import androidx.room.*
import androidx.room.Dao

@Dao
interface UserDao {
    @Query("SELECT * FROM tbl_users")
    fun getUsers(): List<UserEntity>

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}