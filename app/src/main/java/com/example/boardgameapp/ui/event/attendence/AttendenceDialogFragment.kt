package com.example.boardgameapp.ui.event.attendence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.boardgameapp.R
import com.example.boardgameapp.db.BoardGameDatabase
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.boardgameapp.db.entities.Event
import com.example.boardgameapp.databinding.FragmentAttendenceDialogBinding


class AttendenceDialogFragment(private var eventId: Int) : DialogFragment() {

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

        //TODO: Handle via depenency Injection & extract to ViewModel
        /*DB*/
        val db = BoardGameDatabase
        val dao = db.getInstance(requireActivity().application).boardGameDao
        val repository = BoardGameRepository(dao)

        val eventData = repository.getAllEventsNoFlow()
        event = eventData.find { it.id == eventId }!!
        //TODO: Get back in after Event Data are added to Room
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