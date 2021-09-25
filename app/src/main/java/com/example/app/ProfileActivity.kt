package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ProfileActivityBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ProfileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingText.setOnClickListener{
            startActivity(Intent(this,SettingsActivity::class.java))
        }
        binding.aboutText.setOnClickListener {
            startActivity(
                AboutActivity.newInstance(this, "https://www.journaldev.com")
            )
        }
        binding.shareText.setOnClickListener{
            share()
        }
    }
    private fun share(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"GG")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent,null)
        startActivity(shareIntent)
    }
}