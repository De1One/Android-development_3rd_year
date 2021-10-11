package com.example.app.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.databinding.FragmentEventsBinding
import com.example.app.model.EventsReminder

class EventsFragment : Fragment(){
    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    private var eventsAdapter = EventsAdapter()
    private val eventsViewModel: EventsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val recyclerView = binding.recyclerViewEvents.apply{
             adapter = eventsAdapter
             layoutManager = LinearLayoutManager(context)

         }
        eventsViewModel.getEvents().observe(viewLifecycleOwner){
            eventsAdapter.reload(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}