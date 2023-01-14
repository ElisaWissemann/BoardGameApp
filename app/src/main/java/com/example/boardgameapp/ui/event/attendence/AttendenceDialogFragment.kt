package com.example.boardgameapp.ui.event.attendence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.databinding.FragmentAttendenceDialogBinding
import com.example.boardgameapp.ui.event.EventViewModel
import com.example.boardgameapp.ui.event.EventViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class AttendenceDialogFragment() : DialogFragment() {

    private var _binding: FragmentAttendenceDialogBinding? = null
    private val binding get() = _binding!!

    private var eventId: Int = 0

    /**
     * Get instance of EventViewModel
     **/
    private val eventViewModel: EventViewModel by activityViewModels {
        EventViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }

    /**
     * Get instance of Attendence ViewModel**/
    private val viewModel: AttendenceViewModel by activityViewModels {
        AttendenceViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Set a Background with rounded corners for the Dialog
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)

        // Get current eventId from eventViewModel
        lifecycleScope.launch  {
            eventViewModel.eventId.collectLatest {
                eventId = it
            }
        }
        _binding = FragmentAttendenceDialogBinding.inflate(inflater, container, false)

        /**This coroutine makes the attendies available as LiveData to the Fragment*/
        lifecycleScope.launch{ viewModel.setAttendies(eventId) }

        /**Obersving if  accepted users change*/
        viewModel.accepted.observe(this.viewLifecycleOwner) { data ->
            _binding!!.acceptedText.text =
                getString(R.string.acceptedAttendence, data).replace("[", "").replace("]", "")
        }
        viewModel.cancelled.observe(this.viewLifecycleOwner) { data ->
            _binding!!.cancelledText.text =
                getString(R.string.cancelledAttendence, data).replace("[", "").replace("]", "")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.acceptButton.setOnClickListener {
            //execute the corouting on I/O Thread
            lifecycleScope.launch (Dispatchers.IO){
                //flag: 0 accept event / 1 refuse event
                viewModel.updatedEventWithAttendence(0, eventId)
            }
            Toast.makeText(context, "Please reopen the attendence dialog, now steve should be listed as confirmed", Toast.LENGTH_LONG).show()
            dialog?.dismiss()
        }

        binding.refuseButton.setOnClickListener {
            lifecycleScope.launch (Dispatchers.IO){
                // flag: 0 accept event / 1 refuse event
                viewModel.updatedEventWithAttendence(1, eventId)
            }
            Toast.makeText(context, "Please reopen the attendence dialog, now steve should be listed as cancelled", Toast.LENGTH_LONG).show()
            dialog?.dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}