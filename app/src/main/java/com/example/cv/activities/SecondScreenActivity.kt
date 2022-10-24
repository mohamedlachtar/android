package com.example.cv.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import com.example.cv.R
import com.google.android.material.slider.Slider


const val PREF_NAME = "LOGIN_PREF_LOL"
const val IS_REMEMBRED = "IS_REMEMBRED"

class SecondScreenActivity : AppCompatActivity() {

    lateinit var androidSlider: Slider
    lateinit var ios: Slider
    lateinit var flutter: Slider

    lateinit var arabic: CheckBox
    lateinit var french: CheckBox
    lateinit var english: CheckBox

    lateinit var music: CheckBox
    lateinit var sport: CheckBox
    lateinit var games: CheckBox

    var languages = ""
    var hobbies = ""

    lateinit var rememberMe : CheckBox
    lateinit var submit: Button

    lateinit var preference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)
        this.setTitle("Create your resume");

        androidSlider = findViewById<Slider>(R.id.androidSlider)
        ios = findViewById<Slider>(R.id.iosSlider)
        flutter = findViewById<Slider>(R.id.flutterSlider)

        arabic = findViewById<CheckBox>(R.id.checkBox)
        french = findViewById<CheckBox>(R.id.checkBox2)
        english = findViewById<CheckBox>(R.id.checkBox3)

        music = findViewById<CheckBox>(R.id.cbMusic)
        sport = findViewById<CheckBox>(R.id.cbSport)
        games = findViewById<CheckBox>(R.id.cbGames)


        rememberMe = findViewById<CheckBox>(R.id.rememberMeCB)
        submit = findViewById<Button>(R.id.submitButton)
        preference=getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val name:String = intent.getStringExtra("name").toString()
        val email:String = intent.getStringExtra("email").toString()
        val age:String = intent.getStringExtra("age").toString()
        val male:Boolean = intent.getBooleanExtra("gender",true)
        val image:String = intent.getStringExtra("image").toString()


        submit.setOnClickListener{

            if(arabic.isChecked) languages+= " Arabic"
            if(french.isChecked) languages+= " French"
            if(english.isChecked) languages+= " English"

            if(music.isChecked) hobbies+= " Music"
            if(sport.isChecked) hobbies+= " Sport"
            if(games.isChecked) hobbies+= " Games"

            if (rememberMe.isChecked){
                //TODO 4 "Edit the SharedPreferences by putting all the data"
                val editor=preference.edit()
                editor.apply{
                    editor.putString("name",name)
                    editor.putString("email",email)
                    editor.putString("age",age)
                    editor.putBoolean("gender",male)
                    editor.putString("android",androidSlider.value.toString())
                    editor.putString("ios",ios.value.toString())
                    editor.putString("flutter",flutter.value.toString())
                    editor.putString("languages",languages)
                    editor.putString("hobbies",hobbies)
                    editor.putString("image",image)
                    editor.putBoolean(IS_REMEMBRED,true)

                }.apply()


            }

            val intent = Intent(this, Resume2Activity::class.java).apply {
                putExtra("name",name)
                putExtra("email",email)
                putExtra("age",age)
                putExtra("gender",male)
                putExtra("android",androidSlider.value.toString())
                putExtra("ios",ios.value.toString())
                putExtra("flutter",flutter.value.toString())
                putExtra("languages",languages)
                putExtra("hobbies",hobbies)
                putExtra("image",image)
            }
            startActivity(intent)
            finish()
        }

    }
}