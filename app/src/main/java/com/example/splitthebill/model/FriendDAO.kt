package com.example.splitthebill.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FriendDAO {
    @Insert
    fun create(friend: Friend)

    @Query("SELECT * FROM Friend WHERE id = :id")
    fun findOne(id: Int): Friend?

    @Query("SELECT * FROM Friend")
    fun findAll(): MutableList<Friend>

    @Update
    fun update(friend: Friend):Int

    @Delete
    fun delete(friend: Friend):Int
}