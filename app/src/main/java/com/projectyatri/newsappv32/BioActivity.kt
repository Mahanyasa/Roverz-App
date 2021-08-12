package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.projectyatri.newsappv32.ModelClasses.Users
import kotlinx.android.synthetic.main.activity_bio.*
import kotlinx.android.synthetic.main.activity_bio.view.*

class BioActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var firebaseUserId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {


        var refUsers: DatabaseReference?
        val firebaseUser: FirebaseUser?
        //var uid: String?

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio)

        firebaseUser =FirebaseAuth.getInstance().currentUser
        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)
        //val input: DatabaseReference = refUsers.child("Users")
        mAuth = FirebaseAuth.getInstance()
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

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
        bio_edit_btn.setOnClickListener {

                val bio: String = bio_edit.text.toString()

                if(bio == ""){
                    Toast.makeText(this@BioActivity, "Please Enter Bio", Toast.LENGTH_LONG).show()
                }
                else{
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                val userHashMap = HashMap<String, Any>()
                userHashMap["uid"] = firebaseUserId
                userHashMap["bio"] = bio

                refUsers!!.updateChildren(userHashMap).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this@BioActivity, ProfileActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@BioActivity, "Updated Successfully", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

    }
}