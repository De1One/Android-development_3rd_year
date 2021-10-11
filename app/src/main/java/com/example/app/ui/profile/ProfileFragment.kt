package com.example.app.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.AboutActivity
import com.example.app.SettingsActivity
import com.example.app.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        binding.settingText.setOnClickListener{
            startActivity(Intent(it.context, SettingsActivity::class.java))
        }
        binding.aboutText.setOnClickListener {
            startActivity(
                AboutActivity.newInstance(it.context, "https://www.journaldev.com")
            )
        }
        binding.shareText.setOnClickListener{
            share()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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