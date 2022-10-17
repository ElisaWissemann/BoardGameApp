package com.example.boardgameapp.screens.event.attendence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.databinding.FragmentAttendenceDialogBinding


class AttendenceDialogFragment() : DialogFragment() {

    private var _binding: FragmentAttendenceDialogBinding? = null
    //This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    private lateinit var event: Event



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Set a Background with rounded corners for the Dialog
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        //inflate the Layout
        val view = inflater.inflate(R.layout.fragment_attendence_dialog, container, false)
        //ViewBinding
        _binding = FragmentAttendenceDialogBinding.bind(view)


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO get eventID
//        val args: AttendenceDialogFragmentArgs by navArgs()
//        val attendies = EventDataSource.events
//        event = attendies.find { it.id == args.eventId}!!
//
//        binding.confirmedText.text = getString(R.string.confirmedAttendence, event.accepted)
//
//        val cancelled = (eventId = eventId)
//        binding.cancelledText.text = getString(R.string.cancelledAttendence, cancelled.toString())

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