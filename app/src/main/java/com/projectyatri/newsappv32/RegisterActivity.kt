package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        register_btn.setOnClickListener(this)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onClick(view: View?){
        when(view!!.id){
            R.id.register_btn ->{
                val username: String = register_name_edit.text.toString()
                val email: String = register_email_edit.text.toString()
                val password: String = register_password_edit.text.toString()
                val cPassword: String = register_cPassword_edit.text.toString()
                val college: String = register_college_edit.text.toString()

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
                                userHashMap["college"] = college
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