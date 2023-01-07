package com.example.boardgameapp.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.databinding.FragmentDelayedDialogBinding
import com.example.boardgameapp.ui.event.attendence.AttendenceViewModel
import com.example.boardgameapp.ui.event.attendence.AttendenceViewModelFactory
import com.example.boardgameapp.ui.event.delayed.DelayedViewModel
import com.example.boardgameapp.ui.event.delayed.DelayedViewModelFactory


class DelayedDialogFragment() : DialogFragment() {

    private var _binding: FragmentDelayedDialogBinding? = null
    private val binding get() = _binding!!

    private var userId : Int = 0

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

        //_binding = FragmentDelayedDialogBinding.bind(inflater.inflate(R.layout.fragment_delayed_dialog, container, false))
        _binding = FragmentDelayedDialogBinding.inflate(inflater, container, false)

        //Creating minutes delayed Btn's
        binding.delay15min.setOnClickListener {
            Toast.makeText(context, "The other players have been notified about your delay of 15 min", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        //Cancel Dialog Btn
        binding.delay30min.setOnClickListener {
            Toast.makeText(context, "The other players have been notified about your delay of 30 min", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        //Cancel Dialog Btn
        binding.delay45min.setOnClickListener {
            Toast.makeText(context, "The other players have been notified about your delay of 45 min", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        //Cancel Dialog Btn
        binding.delay1h.setOnClickListener {
            Toast.makeText(context, "The other players have been notified about your delay of 60 min", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        //RefuseBtn
        binding.delRefuseBtn.setOnClickListener {
            val UserID = userId // TODO Bodo whats missing here?
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

