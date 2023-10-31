package com.example.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.databinding.FriendComponentBinding
import com.example.splitthebill.model.Friend

class FriendAdapter(context: Context, private val friendList: MutableList<Friend>):
    ArrayAdapter<Friend>(context, R.layout.friend_component, friendList){

    private lateinit var fcb: FriendComponentBinding
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val friend: Friend = friendList[position]
        var componentFriendView = convertView

        if(componentFriendView == null){
            fcb = FriendComponentBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            componentFriendView = fcb.root

            val fcvh = FriendComponentViewHolder(
                componentFriendView.findViewById(R.id.nameTv),
                componentFriendView.findViewById(R.id.valueTv)
            )

            componentFriendView.tag = fcvh
        }

        with(componentFriendView.tag as FriendComponentViewHolder){
            nameTv.text = friend.name
            valueTv.text= friend.value.toString()
        }
        return componentFriendView
    }

    private data class FriendComponentViewHolder(
        val nameTv: TextView,
        val valueTv: TextView,
    )
}