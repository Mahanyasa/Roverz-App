package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.projectyatri.newsappv32.ModelClasses.Users
import kotlinx.android.synthetic.main.activity_college.*

class CollegeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private var firebaseUserId: String = ""
    lateinit var refUsers: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {


        //var refUsers: DatabaseReference?
        val firebaseUser: FirebaseUser?
        //var uid: String?


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_college)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

            college_edit_btn.setOnClickListener (this)
            //val college: String = college_edit.toString()
            }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onClick(view: View?){
        when(view!!.id){
            R.id.college_edit_btn -> {
                    val collegeEdit : String = college_edit.text.toString()
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["college"] = collegeEdit

                    refUsers.updateChildren(userHashMap).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this@CollegeActivity, ProfileActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this@CollegeActivity, "Updated Successfully", Toast.LENGTH_LONG)
                                .show()
                        }
                    }

            }
        }
    }
 }
