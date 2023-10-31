package com.example.splitthebill.controller

import androidx.room.Room
import com.example.splitthebill.model.Friend
import com.example.splitthebill.model.FriendDAO
import com.example.splitthebill.model.FriendRoomDAO
import com.example.splitthebill.view.MainActivity

class FriendController(private val mainActivity: MainActivity) {
    private val friendDAOImpl: FriendDAO = Room.databaseBuilder(
        mainActivity,
        FriendRoomDAO::class.java,
        FriendRoomDAO.FRIEND_DATABASE_FILE,
    ).build().getFriendDao()

    fun getFriend(id: Int) = friendDAOImpl.findOne(id)

    fun getFriends() {
        Thread {
            val person = friendDAOImpl.findAll()
            mainActivity.runOnUiThread {
                mainActivity.updateFriendList(person)
            }
        }.start()
    }

    fun editFriend(friend: Friend){
        Thread {
            friendDAOImpl.update(friend)
            getFriends()
        }.start()
    }

    fun deleteFriend(friend: Friend) {
        Thread {
            friendDAOImpl.delete(friend)
            getFriends()
        }.start()
    }

    fun insert(friend: Friend){
        Thread {
            friendDAOImpl.create(friend)
            getFriends()
        }.start()
    }
}