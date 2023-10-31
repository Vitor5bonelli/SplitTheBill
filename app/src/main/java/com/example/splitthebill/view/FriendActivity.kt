package com.example.splitthebill.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.splitthebill.databinding.ActivityFriendBinding
import com.example.splitthebill.model.Friend

class FriendActivity : BaseActivity() {

    private val afb: ActivityFriendBinding by lazy {
        ActivityFriendBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(afb.root)
        supportActionBar?.subtitle = ("Friend Info!")

        val receivedFriend = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra("Friend", Friend::class.java)
        }
        else{
            intent.getParcelableExtra(EXTRA_FRIEND)
        }

        receivedFriend?.let{ _receivedFriend ->
            with(afb){
                with(_receivedFriend){
                    nameEt.setText(name)
                    valueEt.setText(value.toString())
                    descriptionEt.setText(description)
                }
            }
        }

        val viewFriend = intent.getBooleanExtra(EXTRA_VIEW_FRIEND, false)
        with(afb){
            nameEt.isEnabled = !viewFriend
            valueEt.isEnabled = !viewFriend
            descriptionEt.isEnabled = !viewFriend
            saveBt.visibility = if(viewFriend) View.GONE else View.VISIBLE
        }

        afb.saveBt.setOnClickListener(){
            val friend: Friend = Friend(
                receivedFriend?.id,
                afb.nameEt.text.toString(),
                afb.valueEt.text.toString().toFloat(),
                afb.descriptionEt.text.toString())

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_FRIEND, friend)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

}