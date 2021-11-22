package com.example.app.ui.eventedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.app.databinding.FragmentNewEventBinding
import com.example.app.ui.common.BaseFragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class EventEditFragment : BaseFragment() {

    private var _binding: FragmentNewEventBinding? = null
    private val binding get() = _binding!!

    private val eventEditVM: EventEditVM by viewModels()

    companion object {
        private const val ARG_EVENT_ID = "ARG_EVENT_ID"
        fun newInstance(eventId: Int): Fragment {
            return EventEditFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_EVENT_ID, eventId)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNewEventBinding.inflate(inflater, container, false)
            .apply {
                viewModel = eventEditVM
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(ARG_EVENT_ID)
        id?.let(eventEditVM::loadData)

        initToolbar()
        initDatePicker()
    }

    private fun initToolbar() {
        binding.apply {
            eventEditToolbar.setNavigationOnClickListener { mainRouter.back() }
        }
    }
    private fun initDatePicker() {
        //TODO Добавить реализацию datePicker https://material.io/components/date-pickers/androi
        //TODO Сохранить выбранную дату в ViewModel
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        fragmentManager?.let{
            datePicker.show(it,"")
        }
        //TODO Сохранить выбранную дату в ViewModel
        datePicker.addOnPositiveButtonClickListener() {
            eventEditVM.dateStringObs.set(Date(it))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}