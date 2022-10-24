package com.example.cv.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.cv.R
import com.example.cv.databinding.ActivityRvBinding
import com.example.cv.fragments.AboutMeFragment
import com.example.cv.fragments.CompaniesFragment
import com.example.cv.fragments.EducationFragment
import com.example.cv.fragments.SkillsFragment

class RvActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRvBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("Your Career")
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        replaceFragment(CompaniesFragment())

        binding.companiesButton .setOnClickListener{
            replaceFragment(CompaniesFragment())
        }

        binding.educationButton.setOnClickListener{
            replaceFragment(EducationFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val bundle = Bundle()
        fragment.arguments = bundle
        val fragmentManager =  supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rv_custom_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.addExperience -> {
                val intent = Intent(this, AddExperienceEducationActivity::class.java).apply {
                    putExtra("type","Experience")
                }
                startActivity(intent)
                return true
            }
            R.id.addEducation -> {
                val intent = Intent(this, AddExperienceEducationActivity::class.java).apply {
                    putExtra("type","Education")
                }
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        replaceFragment(CompaniesFragment())
    }
}