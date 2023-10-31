package com.example.splitthebill.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Friend::class], version = 1)
abstract class FriendRoomDAO: RoomDatabase() {
    companion object Constants{
        const val FRIEND_DATABASE_FILE="friends_room"
    }
    abstract fun getFriendDao(): FriendDAO
}