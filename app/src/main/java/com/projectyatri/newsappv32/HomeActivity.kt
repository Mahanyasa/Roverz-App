package com.projectyatri.newsappv32

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.browser.customtabs.CustomTabsIntent
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.projectyatri.newsappv32.ui.ChatActivity
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class   HomeActivity : AppCompatActivity(), NewsItemClicked {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        floating_button.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/Ghoradeshwar/Ghoradeshwar.html")
            startActivity(openURL)
        }
        floating_button_1.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/Kundam%20mala/Kundam%20Mala.html")
            startActivity(openURL)
        }
        floating_button_2.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/Kasarsai%20Dam/Kasarsai%20Dam.html")
            startActivity(openURL)
        }
        floating_button_3.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/Aga%20Khan%20Palace/Aga%20Khan%20Palace.html")
            startActivity(openURL)
        }
        floating_button_4.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/FC%20Road/FC%20Road.html")
            startActivity(openURL)
        }
        floating_button_5.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/Bhandara%20Mountain/Bhandara%20Mountain.html")
            startActivity(openURL)
        }
        floating_button_6.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/Jadhavwadi%20Dam/Jadhavwadi%20Dam.html")
            startActivity(openURL)
        }
        floating_button_7.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/Tiger%20Point/Tiger%20Point.html")
            startActivity(openURL)
        }
        floating_button_8.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://krioddhzit65zk0i4tiryw-on.drv.tw/www.roverz.tech/Goodluck%20Cafe/Goodluck%20Cafe.html")
            startActivity(openURL)
        }

        mAuth = FirebaseAuth.getInstance()
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nav: NavigationView = findViewById(R.id.navView)
        nav.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> {
                    val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.settings -> {
                    val intent = Intent(this@HomeActivity, EditActivity::class.java)
                    startActivity(intent)
                }
                R.id.maps ->{
                    val intent = Intent(this@HomeActivity, TestActivity::class.java)
                    startActivity(intent)
                }
                R.id.updatePassword -> {
                    val intent = Intent(this@HomeActivity, UpdatePasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.search -> {
                    val intent = Intent(this@HomeActivity, SearchActivity::class.java)
                    startActivity(intent)
                }
                R.id.signOut -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this@HomeActivity, WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.chat -> {
                    val intent = Intent(this@HomeActivity, ChatActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}
