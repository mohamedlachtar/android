package com.example.cv.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.cv.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var nameContainer: TextInputLayout
    lateinit var age: EditText
    lateinit var ageContainer: TextInputLayout
    lateinit var email: EditText
    lateinit var emailContainer: TextInputLayout
    lateinit var male: RadioButton
    lateinit var female: RadioButton
    lateinit var next: Button

    val REQUEST_CODE = 100
    lateinit var imageButton: ImageView
    lateinit var image : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setTitle("Create your resume");


        name = findViewById<EditText>(R.id.name)
        nameContainer = findViewById<TextInputLayout>(R.id.nameContainer)
        age = findViewById<EditText>(R.id.age)
        ageContainer = findViewById<TextInputLayout>(R.id.ageContainer)
        email = findViewById<EditText>(R.id.email)
        emailContainer = findViewById<TextInputLayout>(R.id.emailContainer)
        male = findViewById<RadioButton>(R.id.male)
        female = findViewById<RadioButton>(R.id.female)

        imageButton = findViewById(R.id.imageView)

        imageButton.setOnClickListener { openGalleryForImage() }

        name.setOnFocusChangeListener{_,focused ->
            if(!focused)
                nameContainer.helperText = validName(name.text.toString())
        }
        email.setOnFocusChangeListener{_,focused ->
            if(!focused)
                emailContainer.helperText = validEmail(email.text.toString())
        }
        age.setOnFocusChangeListener{_,focused ->
            if(!focused)
                ageContainer.helperText = validAge(age.text.toString())
        }

        next = findViewById<Button>(R.id.nextButton)


        //name.text= nameSH!
        //email.text = emailSH!

        next.setOnClickListener {

            if(validName(name.text.toString())!=null || validEmail(email.text.toString()) != null ||
                validAge(age.text.toString()) != null)
                    return@setOnClickListener


                val intent = Intent(this, SecondScreenActivity::class.java).apply {
                    putExtra("name",name.text.toString())
                    putExtra("email",email.text.toString())
                    putExtra("age",age.text.toString())
                    putExtra("gender",male.isChecked)
                    //putExtra("image",image.data.toString())
                }
                startActivity(intent)

            }
        }


    private fun validName(name: String) : String? {
        if(name.isEmpty())
        {
            return "Type your full name!"
        }
        return null
    }
    private fun validEmail(email: String) : String? {
        if(email.isEmpty())
        {
            return "Type your email!"
        }
        if (!isValidEmail(email.toString())) {
  //          val snack = Snackbar.make(it,"Type a valid email!", Snackbar.LENGTH_LONG)
//            snack.show()
            return "Type a valid email!"

        }
        return null
    }
    private fun validAge(age: String) : String? {
        if(age.isEmpty())
        {
            return "Type your age!"
        }
        return null
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            image = data!!
            imageButton.setImageURI(image.data) // handle chosen image
        }
    }
}