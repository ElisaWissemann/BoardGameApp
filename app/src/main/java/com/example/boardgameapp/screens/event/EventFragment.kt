package com.example.boardgameapp.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.screens.event.hostrating.HostRatingDialog

//TODO: Add Comments
class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    private var _binding: FragmentEventBinding? = null

    // only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    private var host: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEventBinding.inflate(inflater, container, false)

        // return view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO: Move this to the ViewModel and make it MVVM conform
        // Get Data
        val eventData = EventDataSource.events
        val hostData = UserDataSource.users
        // Get EventID passed from upcomingEvents Destination
        val args: EventFragmentArgs by navArgs()
        //extract the event with the ID passed via Navigation
        val event: Event? = eventData.find { it.id == args.eventId }
        host = hostData.find { it.id == event?.host }

        _binding!!.date.text = event?.date
        val hostName = host?.name + " " + host?.surname
        _binding!!.host.text = getString(R.string.event_screen_host, hostName)

    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Event"

        _binding!!.hostRatingButton.setOnClickListener {
            host?.let { it1 ->
                HostRatingDialog(it1.id).show(
                    (activity as AppCompatActivity).supportFragmentManager,
                    "HostRatingDialogFragment"
                )
            }
        }


        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_eventFragment_to_profileFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}