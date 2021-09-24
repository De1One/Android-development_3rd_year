package com.example.app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.page_1 ->{
                    true
                }
                R.id.page_2 ->{
                    true
                }
                R.id.profile ->{
                    startActivity(Intent(this,ProfileActivity::class.java))
                    true
                }
                else ->{
                    false
                }
            }
        }

    }

}



