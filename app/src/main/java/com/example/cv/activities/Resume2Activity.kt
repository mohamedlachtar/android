package com.example.cv.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.cv.R
import com.example.cv.databinding.ActivityResume2Binding
import com.example.cv.fragments.AboutMeFragment
import com.example.cv.fragments.HobbiesFragment
import com.example.cv.fragments.LanguagesFragment
import com.example.cv.fragments.SkillsFragment

class Resume2Activity : AppCompatActivity() {

    private lateinit var binding : ActivityResume2Binding
    lateinit var preference : SharedPreferences
    private lateinit var builder : AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResume2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("Your Resume")

        val name:String = intent.getStringExtra("name").toString()
        val email:String = intent.getStringExtra("email").toString()
        val age:String = intent.getStringExtra("age").toString()
        val gender:Boolean = intent.getBooleanExtra("age",true)
        val androidSkill:String = intent.getStringExtra("android").toString()
        val iosSkill:String = intent.getStringExtra("ios").toString()
        val flutterSkill:String = intent.getStringExtra("flutter").toString()
        val languages:String = intent.getStringExtra("languages").toString()
        val hobbies:String = intent.getStringExtra("hobbies").toString()

        val image = intent.getStringExtra("image")
        val fileUri = Uri.parse(image)
        
        preference=getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        builder = AlertDialog.Builder(this)
        binding.imageView.setImageURI(fileUri)

        binding.nameTextView.text = name
        binding.emailTextView.text = email

        val bundle = skills(androidSkill,iosSkill,flutterSkill)
        replaceFragment(SkillsFragment(),bundle)

        binding.skillsButton.setOnClickListener{
            val bundle = skills(androidSkill,iosSkill,flutterSkill)
            replaceFragment(SkillsFragment(),bundle)
        }
        binding.hobbiesButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("hobbies",hobbies)
            replaceFragment(HobbiesFragment(),bundle)
        }
        binding.languagesButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("languages",languages)
            replaceFragment(LanguagesFragment(),bundle)
        }


        binding.button.setOnClickListener{
            val intent = Intent(Resume2Activity@this, RvActivity::class.java)
            startActivity(intent)
        }


    }

    private fun skills(androidSkill : String,iosSkill : String, flutterSkill : String): Bundle {
        val bundle = Bundle()
        bundle.putString("android",androidSkill)
        bundle.putString("ios",iosSkill)
        bundle.putString("flutter",flutterSkill)
        return bundle;
    }

    private fun replaceFragment(fragment: Fragment, bundle : Bundle){

        fragment.arguments = bundle
        val fragmentManager =  supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val name:String = intent.getStringExtra("name").toString()
        val email:String = intent.getStringExtra("email").toString()
        val age:String = intent.getStringExtra("age").toString()
        val gender:Boolean = intent.getBooleanExtra("age",true)

        return when(item.itemId){
            R.id.info -> {
                val bundle = Bundle()
                var gn ="Male"
                if(!gender) gn = "Female"
                val aboutMe = "Hello! My name is $name\nI'm $age years old\nand i'm a $gn\nYou can contact me via my email\n$email  "
                bundle.putString("aboutMe",aboutMe)
                replaceFragment(AboutMeFragment(),bundle)
                return true
            }
            R.id.logOut -> {

                builder.setTitle("Log Out").setMessage("Are you sure you want to log out?")
                    .setCancelable(true)
                    .setNegativeButton("No"){dialogInterface,it ->
                        dialogInterface.cancel()
                    }
                    .setPositiveButton("Yes"){dialogInterface,it ->
                        getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().clear().apply()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


