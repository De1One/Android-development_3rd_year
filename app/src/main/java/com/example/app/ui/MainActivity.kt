package com.example.app.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.app.R
import com.example.app.databinding.ActivityMainBinding
import com.example.app.ui.events.EventsFragment
import com.example.app.ui.holidays.HolidaysFragment
import com.example.app.ui.newEvent.NewEventFragment
import com.example.app.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, EventsFragment())
                .commit()
            binding.eventsFab.show()
        }
        binding.eventsFab.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame,NewEventFragment())
                .commit()
            binding.eventsFab.hide()
        }
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.events ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, EventsFragment())
                        .commit()
                    binding.eventsFab.show()
                    true
                }
                R.id.holidays ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, HolidaysFragment())
                        .commit()
                    binding.eventsFab.hide()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, ProfileFragment())
                        .commit()
                    binding.eventsFab.hide()
                    true
                }
                else ->{
                    binding.eventsFab.hide()
                    false
                }
            }
        }

    }

}



