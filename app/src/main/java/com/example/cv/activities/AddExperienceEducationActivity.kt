package com.example.cv.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cv.R
import com.example.cv.databinding.ActivityAddExperienceEducationBinding
import com.example.cv.models.Company
import com.example.cv.models.Education
import com.example.cv.utils.AppDataBase
import com.google.android.material.datepicker.MaterialDatePicker
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddExperienceEducationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddExperienceEducationBinding
    val REQUEST_CODE = 100
    lateinit var image : Intent
    private lateinit var  db : AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExperienceEducationBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_experience_education)
        setContentView(binding.root)

        val type:String = intent.getStringExtra("type").toString()
        this.setTitle("Add $type")

        db = AppDataBase.getDatabase(this)


        binding.startDate.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            // Setting up the event for when ok is clicked
            datePicker.addOnPositiveButtonClickListener {
                // formatting date in dd-mm-yyyy format.
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))
                binding.startDate.setText(datePicker.headerText.toString())
            }

            // Setting up the event for when cancelled is clicked
            /*datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }*/
        }

        binding.endDate.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            // Setting up the event for when ok is clicked
            datePicker.addOnPositiveButtonClickListener {
                // formatting date in dd-mm-yyyy format.
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))
                binding.endDate.setText(datePicker.headerText.toString())
            }
        }


        binding.imageView.setOnClickListener{
            openGalleryForImage()
        }


        binding.saveButton.setOnClickListener {
            if (validate()) {

                if(type=="Experience")
                {
                    val experience = Company(id= 0,image= image.data.toString(), name= binding.name.text.toString(), address = binding.address.text.toString(),
                    startDate = binding.startDate.text.toString(), endDate = binding.endDate.text.toString())

                    try {
                        db.companyDao().insert(experience!!)
                        finish()
                    }catch (ex: Exception){
                        Toast.makeText(this, "Could not add the experience !",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    val education = Education(id= 0,image= image.data.toString(), name= binding.name.text.toString(), address = binding.address.text.toString(),
                        startDate = binding.startDate.text.toString(), endDate = binding.endDate.text.toString())

                    try {
                        db.educationDao().insert(education!!)
                        finish()
                    }catch (ex: Exception){
                        Toast.makeText(this, "Could not add the education !",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }

    private fun validate(): Boolean {
        binding.nameContainer.error = null
        binding.addressContainer.error = null
        binding.startDateContainer.error = null
        binding.endDateContainer.error = null

        if (binding.name.text!!.isEmpty()){
            binding.nameContainer.error = "Must not be empty!"
            return false
        }

        if (binding.address.text!!.isEmpty()){
            binding.addressContainer.error = "Must not be empty!"
            return false
        }

        if (binding.startDate.text!!.isEmpty()){
            binding.startDateContainer.error = "Must not be empty!"
            return false
        }

        if (binding.endDate.text!!.isEmpty()){
            binding.endDateContainer.error = "Must not be empty!"
            return false
        }

        return true
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
            binding.imageView.setImageURI(image.data) // handle chosen image
        }
    }
}