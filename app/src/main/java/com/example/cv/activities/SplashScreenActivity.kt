package com.example.cv.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.cv.R

class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler
    lateinit var preference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        preference=getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        handler = Handler()
        handler.postDelayed({

            //TODO 3 "Test in the SharedPreferences if there's data"
            if(preference.getBoolean(IS_REMEMBRED,false))
            {
                val intent = Intent(this, Resume2Activity::class.java).apply {
                    putExtra("name",preference.getString("name",""))
                    putExtra("email",preference.getString("email",""))
                    putExtra("age",preference.getString("age",""))
                    putExtra("gender",preference.getBoolean("gender",false))
                    putExtra("android",preference.getString("android",""))
                    putExtra("ios",preference.getString("ios",""))
                    putExtra("flutter",preference.getString("flutter",""))
                    putExtra("languages",preference.getString("languages",""))
                    putExtra("hobbies",preference.getString("hobbies",""))
                    putExtra("image",preference.getString("image",""))
                }
                startActivity(intent)
            }
            else
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            finish()
        },3000)
    }


}