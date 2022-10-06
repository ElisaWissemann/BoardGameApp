package com.example.boardgameapp.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.screens.event.hostrating.HostRatingDialog

class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    private lateinit var binding: FragmentEventBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEventBinding.inflate(inflater, container, false)

        // return view
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Get Data
        val data = EventDataSource.events
        // Get EventID passed from upcomingEvents Destination
        val args: EventFragmentArgs by navArgs()
        val event = data.find{it.id == args.eventId}


        binding.date.text = event?.date
        binding.host.text = getString(R.string.event_screen_host, event?.host)

    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Event"


        binding.hostRatingButton.setOnClickListener {
            HostRatingDialog().show(
                (activity as AppCompatActivity).supportFragmentManager,
                "HostRatingDialogFragment"
            )
        }
    }

}