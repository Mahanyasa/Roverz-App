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
    val dropDownList = arrayOf("SRM IST Kattankulathur" , "SRM IMH Kattankulathur", "SRM ISH Kattankulathur",
        "SRM IST Ramapuram", "SRM IST Vadapalani", "SRM IST NCR Campus")
    lateinit var firebaseCollege: String
    lateinit var refUsers: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {


        //var refUsers: DatabaseReference?
        val firebaseUser: FirebaseUser?
        //var uid: String?


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_college)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dropDownList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        val editCollege: Spinner = findViewById(R.id.college_edit)
        editCollege.adapter = adapter
        editCollege.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (editCollege.selectedItemPosition == 0){
                    condition1()
                }
                if (editCollege.selectedItemPosition == 1){
                    condition2()
                }
                if (editCollege.selectedItemPosition == 2){
                    condition3()
                }
                if (editCollege.selectedItemPosition == 3){
                    condition4()
                }
                if (editCollege.selectedItemPosition == 4){
                    condition5()
                }
                if (editCollege.selectedItemPosition == 5){
                    condition6()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }

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


    private fun condition1(){

        firebaseCollege = college_edit.selectedItem.toString()
    }
    private fun condition2(){

        firebaseCollege = college_edit.selectedItem.toString()
    }
    private fun condition3(){

        firebaseCollege = college_edit.selectedItem.toString()
    }
    private fun condition4(){

        firebaseCollege = college_edit.selectedItem.toString()
    }
    private fun condition5(){

        firebaseCollege = college_edit.selectedItem.toString()
    }
    private fun condition6(){

        firebaseCollege = college_edit.selectedItem.toString()
    }
    override fun onClick(view: View?){
        when(view!!.id){
            R.id.college_edit_btn -> {
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["college"] = firebaseCollege

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
