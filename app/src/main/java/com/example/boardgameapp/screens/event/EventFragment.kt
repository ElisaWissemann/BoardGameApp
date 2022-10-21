package com.example.boardgameapp.screens.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.node.ViewAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.screens.event.hostrating.HostRatingDialog
import com.example.boardgameapp.screens.upcomingevents.UpcomingEventsAdapter


class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    private lateinit var navController: NavController
    private lateinit var host: User
    private var binding: FragmentEventBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        /* DATABINDING
         * Inflate the view and obtain an instance of the binding class
         * Documentation: https://developer.android.com/topic/libraries/data-binding
         * CodeLab: https://developer.android.com/codelabs/android-databinding#2
         */
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_event,
            container,
            false
        )

        /*VIEWMODEL*/
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        binding?.eventViewModel = viewModel
        binding?.lifecycleOwner = this
        navController = findNavController()
        // return view
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Move this to the ViewModel and make it MVVM conform
        // Get Data
        //TODO: Pass args to the ViewModel via Hilt
        //extract the event with the ID passed via Navigation
        val args: EventFragmentArgs by navArgs()
        //save the eventId in the Bundle
        savedInstanceState?.putInt("eventId", args.eventId)
        //initialize host
        host = viewModel.host.value!!
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Event"

        //create and show HostRatigDialog
        binding?.hostRatingButton?.setOnClickListener {
            HostRatingDialog(host.id).show(
                (activity as AppCompatActivity).supportFragmentManager,
                "HostRatingDialogFragment"
            )
        }

        //Navigate from EventScreen to ProfileScreen
        binding!!.profileButton.setOnClickListener {
            val action =
                viewModel.hostId.value?.let { it1 -> EventFragmentDirections.actionEventFragmentToProfileFragment(pUserId = it1) }
            if (action != null) {
                navController.navigate(action)
            }
        }

        binding!!.chooseGamesButton.setOnClickListener {

                navController.navigate(R.id.action_eventFragment_to_chooseGamesFragment4)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
