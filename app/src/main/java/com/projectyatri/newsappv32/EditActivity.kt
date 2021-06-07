package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        supportActionBar?.hide()


        edit_profileImage_edit.setOnClickListener {
            val intent = Intent(this@EditActivity, ProfileImageActivity::class.java)
            startActivity(intent)
        }
        edit_insta_edit.setOnClickListener {
            val intent = Intent(this@EditActivity, InstaActivity::class.java)
            startActivity(intent)
        }
        edit_college_edit.setOnClickListener {
            val intent = Intent(this@EditActivity, CollegeActivity::class.java)
            startActivity(intent)
        }
        edit_bio_edit.setOnClickListener {
            val intent = Intent(this@EditActivity, BioActivity::class.java)
            startActivity(intent)
        }
    }
}