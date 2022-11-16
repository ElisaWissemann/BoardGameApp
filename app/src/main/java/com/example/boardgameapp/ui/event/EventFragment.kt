package com.example.boardgameapp.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.boardgameapp.repositories.dto.GameNight
import com.example.boardgameapp.ui.event.hostrating.HostRatingDialog


class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private val args: EventFragmentArgs by navArgs()
    lateinit var gameNight: GameNight

    private val viewModel: EventViewModel by activityViewModels {
        EventViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }

    private lateinit var navController: NavController

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentEventBinding.inflate(inflater, container, false)

        /*Navigation*/
        navController = findNavController()

        return binding.root
    }


    private fun bind(gameNight: GameNight) {
        binding.apply {
            date.text = gameNight.date
            host.text = getString(R.string.hostet_by, gameNight.host)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Retrieves an GameNight Object with the given eventId and hostIde*/
        viewModel.retrieveGameNight(args.eventId, args.hostId)
            .observe(this.viewLifecycleOwner) { thisGameNight ->
                gameNight = thisGameNight
                bind(gameNight)
            }
    }

    override fun onStart() {
        super.onStart()
        //Set AppBarTitle
        (activity as AppCompatActivity).supportActionBar?.title = "Event"

        /**
         * Opens the HostRatingDialog
         * */
        //TODO: Fix if there is no value in the DB
        binding?.hostRatingButton?.setOnClickListener {
            HostRatingDialog(gameNight.hostRating).show(
                (activity as AppCompatActivity).supportFragmentManager,
                "HostRatingDialogFragment"
            )
        }

        /**
         * Opens the DelayedDialog
         * */
        binding?.delayedButton?.setOnClickListener {
            DelayedDialogFragment(gameNight.hostId).show(
                (activity as AppCompatActivity).supportFragmentManager, "DelayedDialogFragment"
            )

        }

        /**
         * Navigates to the ProfileScreen of the Host
         * */
        binding.profileButton.setOnClickListener {
            navController.navigate(
                EventFragmentDirections.actionEventFragmentToProfileFragment(
                    pUserId = gameNight.hostId
                )
            )
        }

        /**
         * Navigates to the ChooseGamesFragment
         * */
        binding.chooseGamesButton.setOnClickListener {
            navController.navigate(R.id.action_eventFragment_to_chooseGamesFragment4)
        }
    }

    /**
     * Called when fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        //destroy Binding
        _binding = null
    }
}