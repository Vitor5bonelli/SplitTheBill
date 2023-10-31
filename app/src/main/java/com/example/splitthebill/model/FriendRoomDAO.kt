package com.example.splitthebill.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Friend::class], version = 1)
abstract class PersonDaoRoom: RoomDatabase() {
    companion object Constants{
        const val PERSON_DATABASE_FILE="friends_room"
    }
    abstract fun getPersonDao(): FriendDAO
}