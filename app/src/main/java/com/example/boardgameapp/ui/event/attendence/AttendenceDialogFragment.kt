package com.example.boardgameapp.ui.event.attendence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.db.BoardGameDatabase
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.boardgameapp.db.entities.Event
import com.example.boardgameapp.databinding.FragmentAttendenceDialogBinding
import com.example.boardgameapp.ui.event.EventViewModel
import com.example.boardgameapp.ui.event.EventViewModelFactory


class AttendenceDialogFragment(private var eventId: Int) : DialogFragment() {

    private var _binding: FragmentAttendenceDialogBinding? = null
    //This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    //private lateinit var event: Event

    private val viewModel: EventViewModel by activityViewModels {
        EventViewModelFactory(
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
        _binding = FragmentAttendenceDialogBinding.bind(inflater.inflate(R.layout.fragment_attendence_dialog, container, false))

        //_binding!!.confirmedText.text = getString(R.string.confirmedAttendence, event.accepted).replace("[", "").replace("]", "")
        //_binding!!.cancelledText.text = getString(R.string.cancelledAttendence, event.cancelled).replace("[", "").replace("]", "")


        binding.acceptButton.setOnClickListener {
            //TODO I take part
            //TODO close dialog
        }

        binding.refuseButton.setOnClickListener {
            //TODO I don't take part
            //TODO close dialog
        }


        return view
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