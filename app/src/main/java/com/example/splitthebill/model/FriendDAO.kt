package com.example.splitthebill.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FriendDAO {
    @Insert
    fun create(person: Friend)

    @Query("SELECT * FROM Friend WHERE id = :id")
    fun findOne(id: Int): Friend?

    @Query("SELECT * FROM Friend")
    fun findAll(): MutableList<Friend>

    @Update
    fun update(person: Friend) :Int

    @Delete
    fun delete(person: Friend):Int
}