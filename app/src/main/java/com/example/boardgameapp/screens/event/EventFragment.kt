package com.example.boardgameapp.screens.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.screens.choosegames.ChooseGamesFragmentDirections
import com.example.boardgameapp.screens.event.attendence.AttendenceDialogFragment
import com.example.boardgameapp.screens.event.hostrating.HostRatingDialog
import com.example.boardgameapp.screens.game.GameFragmentDirections
import com.example.boardgameapp.screens.profile.ProfileFragmentDirections


//TODO: Add Comments
class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: EventViewModel
    private lateinit var navController: NavController
    private lateinit var host: User
    private lateinit var event: Event
    private lateinit var binding: FragmentEventBinding

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
        //binding = FragmentEventBinding.bind(binding.root)


        /*VIEWMODEL*/
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        binding.eventViewModel = viewModel
        binding.setLifecycleOwner(this)
        navController = findNavController()

        // return view
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Move this to the ViewModel and make it MVVM conform
        // Get Data
        //extract the event with the ID passed via Navigation
        //TODO: Pass args to the ViewModel via Hilt
        val args: EventFragmentArgs by navArgs()
        if (savedInstanceState != null) {
            savedInstanceState.putInt("eventId", args.eventId)
        }
        host = viewModel.host.value!!
        event = viewModel.event.value!!
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Event"

        //TODO Move this to the ViewModel and observe ClickListener in layout_ressource
        binding!!.hostRatingButton.setOnClickListener {
            HostRatingDialog(host.id).show(
                (activity as AppCompatActivity).supportFragmentManager,
                "HostRatingDialogFragment"
            )
        }


        binding.chooseGamesButton.setOnClickListener {
                navController.navigate(R.id.action_eventFragment_to_chooseGamesFragment4)

        }

        binding!!.profileButton.setOnClickListener {
             val action =
                viewModel.hostId.value?.let { it1 -> ProfileFragmentDirections.actionGlobalProfileFragment(pUserId = it1) }
            if (action != null) {
                navController.navigate(action)
            }
        }

        binding!!.attendenceButton.setOnClickListener {
            AttendenceDialogFragment(event.id).show(
                (activity as AppCompatActivity).supportFragmentManager,
                "AttendenceDialogFragment"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }


}

