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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.BoardGameDatabase
import com.example.boardgameapp.data.BoardGameRepository
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.screens.event.hostrating.HostRatingDialog


class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var navController: NavController
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

        /*Navigation*/
        navController = findNavController()
        val args: EventFragmentArgs by navArgs()

        /*DB*/
        val db = BoardGameDatabase
        val dao = db.getInstance(requireActivity().application).boardGameDao
        val repository = BoardGameRepository(dao)

        /*ViewModel*/
        //TODO add Hilt dependency injection to acces NavArgs in ViewModel
        val factory = EventViewModelFactory(repository, args.eventId)
        binding?.eventViewModel = ViewModelProvider(this, factory).get(EventViewModel::class.java)

        binding?.lifecycleOwner = this

        // return view
        return binding!!.root
    }

    override fun onStart() {
        super.onStart()
        //Set AppBarTitle
        (activity as AppCompatActivity).supportActionBar?.title = "Event"

        //get current HostId from ViewModel
        val hostId = binding?.eventViewModel?.hostId?.value!!

        //create and show HostRatingDialog
        binding?.hostRatingButton?.setOnClickListener {
            HostRatingDialog(hostId).show(
                (activity as AppCompatActivity).supportFragmentManager,
                "HostRatingDialogFragment"
            )
        }

        //Navigate from EventScreen to ProfileScreen
        binding!!.profileButton.setOnClickListener {
            navController.navigate(
                EventFragmentDirections.actionEventFragmentToProfileFragment(
                    pUserId = hostId
                )
            )
        }

        binding!!.chooseGamesButton.setOnClickListener {
            navController.navigate(R.id.action_eventFragment_to_chooseGamesFragment4)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //destroy Binding
        binding = null
    }
}
