package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        login_btn.setOnClickListener {
            loginUser()
        }
        login_forgot_text.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun loginUser() {
        val email: String = login_email_edit.text.toString()
        val password: String = login_password_edit.text.toString()

        when {
            email == "" -> {
                Toast.makeText(this@LoginActivity, "Please enter Email", Toast.LENGTH_LONG).show()
            }
            password == "" -> {
                Toast.makeText(this@LoginActivity, "Please enter password", Toast.LENGTH_LONG).show()
            }
            else -> {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task->
                        if (task.isSuccessful){
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this@LoginActivity, "Error Message: " + task.exception!!.message.toString(),
                                Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

    }
}