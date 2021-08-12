package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.projectyatri.newsappv32.ModelClasses.Users
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity() : AppCompatActivity(), SearchItemClicked {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private lateinit var arrayList: ArrayList<Users>
    private var firebaseUserId: String = ""
    private var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

        search_recyclerView.layoutManager = LinearLayoutManager(this)
        search_recyclerView.setHasFixedSize(true)

        arrayList = arrayListOf<Users>()
        getUserData()
        firebaseUser =FirebaseAuth.getInstance().currentUser
        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)
        //val input: DatabaseReference = refUsers.child("Users")
        mAuth = FirebaseAuth.getInstance()

        refUsers.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    val user: Users? = p0.getValue(Users::class.java)

                    firebaseUserId = user!!.getUID().toString()

                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

    }


    private fun getUserData() {

        refUsers = FirebaseDatabase.getInstance().reference.child("Users")//.child(firebaseUser!!.uid)
        refUsers.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for(userSnapshot in p0.children){
                        val users: Users? = userSnapshot.getValue(Users::class.java)
                        arrayList.add(users!!)

                    }
                    search_recyclerView.adapter = MyAdapterUsers(arrayList, this)

                    }
                }




            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
    override fun onItemClicked(item: Users){

    }

}