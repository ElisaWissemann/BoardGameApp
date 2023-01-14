package com.example.boardgameapp.ui.event

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.BoardGameNotification
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.databinding.FragmentDelayedDialogBinding
import com.example.boardgameapp.ui.event.attendence.AttendenceViewModel
import com.example.boardgameapp.ui.event.attendence.AttendenceViewModelFactory
import com.example.boardgameapp.ui.event.delayed.DelayedViewModel
import com.example.boardgameapp.ui.event.delayed.DelayedViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class DelayedDialogFragment() : DialogFragment() {

    private var _binding: FragmentDelayedDialogBinding? = null
    private val binding get() = _binding!!

    private var userId: Int = 0
    private var userName: String = ""

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
    private val viewModel: DelayedViewModel by activityViewModels {
        DelayedViewModelFactory(
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
        //get Id of the current User
        userId = viewModel.loggedInUserId
        // get the usersName
        userName = viewModel.getUserName(userId)
        // Get current eventId from eventViewModel
        lifecycleScope.launch {
            eventViewModel.eventId.collectLatest {
                eventId = it
            }
        }        // get Instance of the notificationService
        val notificationService = activity?.let { BoardGameNotification(it.applicationContext) }

        //_binding = FragmentDelayedDialogBinding.bind(inflater.inflate(R.layout.fragment_delayed_dialog, container, false))
        _binding = FragmentDelayedDialogBinding.inflate(inflater, container, false)


        /**
         * Delayed Buttons
         * */


        binding.delay15min.setOnClickListener {
            notificationService?.showNotification(userName, "15")
            Toast.makeText(context, "Please check the notifications", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        binding.delay30min.setOnClickListener {
            notificationService?.showNotification(userName, "30")
            Toast.makeText(context, "Please check the notifications", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        binding.delay45min.setOnClickListener {
            notificationService?.showNotification(userName, "45")
            Toast.makeText(context, "Please check the notifications", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        binding.delay60min.setOnClickListener {
            notificationService?.showNotification(userName, "60")
            Toast.makeText(context, "Please check the notifications", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        binding.refuse.setOnClickListener {
            //execute the corouting on I/O Thread
            lifecycleScope.launch(Dispatchers.IO) {
                //flag: 0 accept event / 1 refuse event
                viewModel.updatedEventWithAttendence(1, eventId)
            }
            Toast.makeText(context, "Go to Attendence if steve cancelled the event it worked", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        return binding.root
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

