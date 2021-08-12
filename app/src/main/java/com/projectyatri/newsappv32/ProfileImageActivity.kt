@file:Suppress("DEPRECATION")

package com.projectyatri.newsappv32


import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.projectyatri.newsappv32.ModelClasses.Users
import com.projectyatri.newsappv32.databinding.ActivityProfileImageBinding
import kotlinx.android.synthetic.main.activity_profile_image.*
import java.util.*

class ProfileImageActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var firebaseUserId: String = ""
    lateinit var binding: ActivityProfileImageBinding
    lateinit var imageUri: Uri
    lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {


        val refUsers: DatabaseReference?
        val firebaseUser: FirebaseUser?

        super.onCreate(savedInstanceState)

        binding = ActivityProfileImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

                    uid = user!!.getUID().toString()

                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

        firebaseUserId = mAuth.currentUser!!.uid

        binding.profileImageChooseBtn.setOnClickListener{
            chooseImage()
        }
        binding.profileImageUploadBtn.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File ....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        //val formatter = SimpleDateFormat("yyyy_mm_dd", Locale.getDefault())
        //val now =Date()
        //val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$firebaseUserId")

        storageReference.putFile(imageUri).
                addOnSuccessListener {

                    binding.imgView.setImageURI(null)
                    val intent = Intent(this@ProfileImageActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@ProfileImageActivity, "Successfully Uploaded the Image", Toast.LENGTH_LONG).show()
                    if(progressDialog.isShowing) progressDialog.dismiss()

                }.addOnFailureListener{

                    if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@ProfileImageActivity, "Unable to Upload Image", Toast.LENGTH_LONG).show()
        }
    }

    private fun chooseImage() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK){
            imageUri = data?.data!!
            binding.imgView.setImageURI(imageUri)

        }
    }

}
