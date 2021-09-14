package com.projectyatri.newsappv32

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_update_password.*

class UpdatePasswordActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)

        mAuth = FirebaseAuth.getInstance()
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        update_password_btn.setOnClickListener {
            updatePassword()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updatePassword() {
        var password: String = update_password_edit.text.toString()
        val cPassword: String = update_cPassword_edit.text.toString()

        if (password == "" || cPassword == "") {
            Toast.makeText(this@UpdatePasswordActivity, "Please enter password", Toast.LENGTH_LONG).show()
        }
        else if(password != cPassword){
            Toast.makeText(this@UpdatePasswordActivity, "Password did not match", Toast.LENGTH_LONG).show()
        }
        else {
            mAuth.currentUser?.updatePassword(password)
                ?.addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password changes successfully", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@UpdatePasswordActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "password not changed", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }
}