package com.example.splitthebill.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.splitthebill.R
import com.example.splitthebill.adapter.FriendAdapter
import com.example.splitthebill.controller.FriendController
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Friend

class MainActivity : BaseActivity() {
    private val amb : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val friendList : MutableList<Friend> = mutableListOf()

    private val friendAdapter: FriendAdapter by lazy {
        FriendAdapter(this, friendList)
    }
    private lateinit var parl: ActivityResultLauncher<Intent>

    private val friendController: FriendController by lazy {
        FriendController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        supportActionBar?.subtitle = "Friendlist!"
        friendController.getFriends()

        amb.partyLV.adapter = friendAdapter

        parl =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val friend = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra("Friend", Friend::class.java)
                    } else {
                        result.data?.getParcelableExtra<Friend>(EXTRA_FRIEND)
                    }

                    friend?.let { _friend ->
                        val position = friendList.indexOfFirst { it.id == _friend.id }
                        if (position != -1) {
                            friendList[position] = _friend
                            friendController.editFriend(_friend)
                            Toast.makeText(this, "Friend edited!", Toast.LENGTH_SHORT).show()
                        } else {
                            friendController.insert(_friend)
                            friendController.getFriends()
                            Toast.makeText(this, "Friend Added!", Toast.LENGTH_SHORT).show()
                        }
                        friendAdapter.notifyDataSetChanged()
                    }
                }
            }
        registerForContextMenu(amb.partyLV)

        amb.partyLV.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                val person = friendList[position]
                val personIntent = Intent(this@MainActivity, FriendActivity::class.java)
                personIntent.putExtra(EXTRA_FRIEND, person)
                personIntent.putExtra(EXTRA_VIEW_FRIEND, true)
                parl.launch(personIntent)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addPersonMi -> {
                parl.launch(Intent(this, FriendActivity::class.java))
                true
            }
            R.id.splitBillMi -> {
                val splitIntent = Intent(this, SplitTheBillActivity::class.java)
                splitIntent.putParcelableArrayListExtra(EXTRA_SPLIT, ArrayList<Friend>(friendList))
                parl.launch(splitIntent)
                true
            }
            else ->false
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position =(item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        val person = friendList[position]
        return when(item.itemId){
            R.id.editPersonMi -> {
                val person = friendList[position]
                val personIntent = Intent(this, FriendActivity::class.java)
                personIntent.putExtra(EXTRA_FRIEND, person)
                parl.launch(personIntent)
                true
            }
            R.id.removePersonMi -> {

                friendList.removeAt(position)
                friendController.deleteFriend(person)
                friendAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Friend Removed!", Toast.LENGTH_SHORT).show()

                true
            }
            else -> false
        }
    }

    fun updateFriendList(_friendList: MutableList<Friend>){
        friendList.clear()
        friendList.addAll(_friendList)
        friendAdapter.notifyDataSetChanged()

    }

}
