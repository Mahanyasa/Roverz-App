package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

@Suppress("NAME_SHADOWING")
class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""
    val dropDownList = arrayOf(
        "SRM IST Kattankulathur", "SRM IMH Kattankulathur", "SRM ISH Kattankulathur",
        "SRM IST Ramapuram", "SRM IST Vadapalani", "SRM IST NCR Campus"
    )
    lateinit var firebaseCollege: String

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dropDownList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        register_college_edit.adapter = adapter
        register_college_edit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (register_college_edit.selectedItemPosition == 0){
                    condition1()
                }
                if (register_college_edit.selectedItemPosition == 1){
                    condition2()
                }
                if (register_college_edit.selectedItemPosition == 2){
                    condition3()
                }
                if (register_college_edit.selectedItemPosition == 3){
                    condition4()
                }
                if (register_college_edit.selectedItemPosition == 4){
                    condition5()
                }
                if (register_college_edit.selectedItemPosition == 5){
                    condition6()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }

        register_btn.setOnClickListener(this)
        }

    private fun condition1() {
        //Toast.makeText(this@CollegeActivity, "Selected Item" + college_edit.selectedItem, Toast.LENGTH_LONG ).show()
        firebaseCollege = register_college_edit.selectedItem.toString()
    }

    private fun condition2() {
        //Toast.makeText(this@CollegeActivity, "Selected Item" + college_edit.selectedItem, Toast.LENGTH_LONG ).show()
        firebaseCollege = register_college_edit.selectedItem.toString()
    }

    private fun condition3() {
        //Toast.makeText(this@CollegeActivity, "Selected Item" + college_edit.selectedItem, Toast.LENGTH_LONG ).show()
        firebaseCollege = register_college_edit.selectedItem.toString()
    }

    private fun condition4() {
        //Toast.makeText(this@CollegeActivity, "Selected Item" + college_edit.selectedItem, Toast.LENGTH_LONG ).show()
        firebaseCollege = register_college_edit.selectedItem.toString()
    }

    private fun condition5() {
        //Toast.makeText(this@CollegeActivity, "Selected Item" + college_edit.selectedItem, Toast.LENGTH_LONG ).show()
        firebaseCollege = register_college_edit.selectedItem.toString()
    }

    private fun condition6() {
        //Toast.makeText(this@CollegeActivity, "Selected Item" + college_edit.selectedItem, Toast.LENGTH_LONG ).show()
        firebaseCollege = register_college_edit.selectedItem.toString()
    }
    override fun onClick(view: View?){
        when(view!!.id){
            R.id.register_btn ->{
                val username: String = register_name_edit.text.toString()
                val email: String = register_email_edit.text.toString()
                val password: String = register_password_edit.text.toString()
                val cPassword: String = register_cPassword_edit.text.toString()
                //val college: String = register_college_edit.text.toString()

                if (username == "" || email == "" || password == "" || cPassword == "" ) {
                    Toast.makeText(this@RegisterActivity, "Please Enter All Fields Correctly",
                        Toast.LENGTH_LONG
                    ).show()
                }
                //else if (college != "SRM IST"){
                //Toast.makeText(this@RegisterActivity, "We are only providing our service to SRM IST students", Toast.LENGTH_LONG).show()
                //}
                else if (password != cPassword) {
                    Toast.makeText(this@RegisterActivity, "Password Did Not Match", Toast.LENGTH_LONG)
                        .show()
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                firebaseUserId = mAuth.currentUser!!.uid
                                refUsers = FirebaseDatabase.getInstance().reference.child("Users")
                                    .child(firebaseUserId)

                                val userHashMap = HashMap<String, Any>()
                                userHashMap["uid"] = firebaseUserId
                                userHashMap["username"] = username
                                userHashMap["college"] = firebaseCollege
                                userHashMap["email"] = email

                                refUsers.updateChildren(userHashMap).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val intent =
                                            Intent(this@RegisterActivity, ProfileActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)
                                        finish()
                                    }
                                }

                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Error Message: " + task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
                    }
                }
            }
        }
