package com.example.app


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ActivityMainBinding
import com.example.app.fragments.EventsFragment
import com.example.app.fragments.HolidaysFragment
import com.example.app.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.events ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame,EventsFragment())
                        .commit()
                    true
                }
                R.id.holidays ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame,HolidaysFragment())
                        .commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame,ProfileFragment())
                        .commit()
                    //startActivity(Intent(this,ProfileActivity::class.java))
                    true
                }
                else ->{
                    false
                }
            }
        }

    }

}



