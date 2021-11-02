package com.example.app.ui.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.AboutActivityBinding

class AboutActivity: AppCompatActivity() {

    private lateinit var binding: AboutActivityBinding

    companion object {
        private const val EXTRA_URL = "EXTRA_URL"

        fun newInstance(context: Context, url: String) = Intent(context, AboutActivity::class.java).apply {
            putExtra(EXTRA_URL, url)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AboutActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra(EXTRA_URL) ?: ""

        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }
}