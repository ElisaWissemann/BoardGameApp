package com.example.boardgameapp.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.screens.event.hostrating.HostRatingDialog
import com.example.boardgameapp.screens.upcomingevents.UpcomingEventsFragmentDirections

//TODO: Add Comments
class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    private var _binding: FragmentEventBinding? = null
    private lateinit var navController: NavController

    // only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    private lateinit var host: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEventBinding.inflate(inflater, container, false)
        navController = findNavController()

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
        // !! tells the system that the host will never be null, will be catched on database
        host = hostData.find { it.id == event?.host }!!

        _binding!!.date.text = event?.date
        val hostName = host?.name + " " + host?.surname

        // override host text getString from the res and replace %1s with the hostname
        _binding!!.host.text = getString(R.string.event_screen_host, hostName)

    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Event"

        _binding!!.hostRatingButton.setOnClickListener {
            HostRatingDialog(host.id).show(
                (activity as AppCompatActivity).supportFragmentManager,
                "HostRatingDialogFragment"
            )
        }
        
        binding.profileButton.setOnClickListener {
            val action =
                EventFragmentDirections.actionEventFragmentToProfileFragment(pUserId = it.id)
            navController.navigate(action)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}