package com.example.app.ui.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.app.R
import com.example.app.databinding.ActivityMainBinding
import com.example.app.ui.common.App
import com.example.app.ui.events.EventsFragment
import com.example.app.ui.holidays.HolidaysFragment
import com.example.app.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainVM: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).mainRouter = MainRouter(supportFragmentManager)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) updateMainContent(EventsFragment())

        initNavigation()
    }
    private fun initNavigation() {
        binding.bottomNavigation.apply {
            setOnItemSelectedListener {
                mainVM.saveCurrentMenuId(it.itemId)
                when (it.itemId) {
                    R.id.events -> updateMainContent(EventsFragment())
                    R.id.holidays -> updateMainContent(HolidaysFragment())
                    R.id.profile -> updateMainContent(ProfileFragment())
                    else -> false
                }
            }
            selectedItemId = mainVM.getLastMenuId()
        }
    }
    private fun updateMainContent(newFragment: Fragment): Boolean {
        val tag = newFragment::class.java.name
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFrame.id, newFragment, tag)
            .commit()
        return true
    }
}



