package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mAuth = FirebaseAuth.getInstance()
        forgot_password_btn.setOnClickListener {
            forgotPassword()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun forgotPassword() {
        val email: String = forgot_email_edit.text.toString()

        if(email == ""){
            Toast.makeText(this@ForgotPasswordActivity, "Please Enter Your Email Address", Toast.LENGTH_LONG).show()
        }
        else{
            mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG)
                            .show()
                        val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Unable to send reset mail", Toast.LENGTH_LONG)
                            .show()
                    }
                })
        }

    }
}