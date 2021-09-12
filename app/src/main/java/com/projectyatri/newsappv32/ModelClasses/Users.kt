package com.projectyatri.newsappv32.ModelClasses

class Users {
    private var uid: String = ""
    private var username: String = ""
    private var email: String = ""
    private var college: String = ""
    private var bio: String = "Need to enter Bio"
    private var insta: String = "Did Not add Instagram Username"


    constructor()


    constructor(
        uid: String,
        username: String,
        email: String,
        college: String,
        bio: String,
        insta: String,
    ) {
        this.uid = uid
        this.username = username
        this.email = email
        this.college = college
        this.bio = bio
        this.insta = insta


    }

    fun getUID(): String? {
        return uid
    }
    fun setUID(uid: String) {
        this.uid = uid
    }

    fun getUserName(): String? {
        return username
    }
    fun setUserName(username: String) {
        this.username = username
    }

    fun getEmail(): String?{
        return email
    }
    fun setEmail(email: String){
        this.email = email
    }

    fun getCollege(): String?{
        return college
    }
    fun setCollege(college: String){
        this.college = college
    }

    fun getBio(): String?{
        return bio
    }
    fun setBio(bio: String){
        this.bio = bio
    }

    fun getInsta(): String?{
        return insta
    }
    fun setInsta(insta: String){
        this.insta = insta
    }

}