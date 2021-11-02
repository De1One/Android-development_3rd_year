package com.example.app.ui.eventdescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.app.R
import com.example.app.databinding.FragmentEventDescriptionBinding
import com.example.app.domain.model.EventsReminder
import com.example.app.ui.common.BaseFragment


class EventDescriptionFragment : BaseFragment() {

    private var _binding: FragmentEventDescriptionBinding? = null
    private val binding get() = _binding!!

    private val eventDescriptionVM: EventDescriptionVM by viewModels()

    companion object {
        private const val ARG_EVENT_ID = "ARG_EVENT_ID"
        fun newInstance(eventId: Int): Fragment {
            return EventDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_EVENT_ID, eventId)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEventDescriptionBinding.inflate(inflater, container, false)
        // TODO: Привязать EventDescriptionVM к binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(ARG_EVENT_ID)
        id?.let(eventDescriptionVM::loadData)
        binding.apply {
            eventDescToolbar.setNavigationOnClickListener { mainRouter.back() }
        }
        initMenu()
    }

    private fun initMenu() {
        binding.eventDescToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.eventDescMenu_remove -> eventDescriptionVM.removeEvent()
                R.id.eventDescMenu_edit   -> eventDescriptionVM.editEvent()
                else                      -> {
                }
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}