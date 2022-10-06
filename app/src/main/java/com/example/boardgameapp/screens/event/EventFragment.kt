package com.example.boardgameapp.screens.event

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource
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
        val eventData = EventDataSource.events
        val hostData = UserDataSource.users
        // Get EventID passed from upcomingEvents Destination
        val args: EventFragmentArgs by navArgs()
        //extract the event with the ID passed via Navigation
        val event: Event? = eventData.find{it.id == args.eventId}
        val host: User? = hostData.find { it.id == event?.host }

        binding.date.text = event?.date
        val hostName = host?.name + " " + host?.surname
        Log.i("ELISA", getString(R.string.event_screen_host, hostName))
        binding.host.text = getString(R.string.event_screen_host, hostName)

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