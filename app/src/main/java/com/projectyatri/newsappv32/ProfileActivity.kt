@file:Suppress("DEPRECATION")

package com.projectyatri.newsappv32

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.projectyatri.newsappv32.ModelClasses.Users
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.File

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private var firebaseUserId: String = ""
    lateinit var insta: String
    lateinit var college: String
    lateinit var uid: String


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {


        val refUsers: DatabaseReference?
        val firebaseUser: FirebaseUser?
        //var insta: String

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)
        //val input: DatabaseReference = refUsers.child("Users")
        mAuth = FirebaseAuth.getInstance()
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

        refUsers.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    val user: Users? = p0.getValue(Users::class.java)

                    profile_username_text.text = user!!.getUserName()
                    profile_college_text.text = user.getCollege()
                    profile_bio_text.text = user.getBio()
                    profile_insta_text.text = user.getInsta()
                    insta = user.getInsta().toString()
                    college = user.getCollege().toString()
                    uid = user.getUID().toString()

                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

        firebaseUserId = mAuth.currentUser!!.uid

        profileImage.setOnClickListener (this)
        profile_insta_text.setOnClickListener(this)
        profile_college_text.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.profile_insta_text ->{
                val instaConcat = "https://www.instagram.com/$insta"
                val openURL = Intent(android.content.Intent.ACTION_VIEW)
                openURL.data = Uri.parse(instaConcat)
                startActivity(openURL)
            }
            R.id.profile_college_text -> {
                val collegeConCat = "https://www.google.com/search?q=$college"
                val openURL = Intent(android.content.Intent.ACTION_VIEW)
                openURL.data = Uri.parse(collegeConCat)
                startActivity(openURL)
            }
            R.id.profileImage ->{
                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Loading Message..")
                progressDialog.setCancelable(false)
                progressDialog.show()
                //val imageName = "jL5trufZK0OSnppftU9mrv1Rup42"
                val storageRef = FirebaseStorage.getInstance().reference.child("images/$firebaseUserId")
                val localFile = File.createTempFile("tempImage", "jpg")
                storageRef.getFile(localFile).
                addOnSuccessListener {

                    if(progressDialog.isShowing) progressDialog.dismiss()

                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    profileImage.setImageBitmap(bitmap)

                }.addOnFailureListener{
                    if (progressDialog.isShowing) progressDialog.dismiss()
                    Toast.makeText(this@ProfileActivity, "Failed to load the image", Toast.LENGTH_LONG).show()

                }
            }
        }
    }
}


