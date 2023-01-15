package com.example.boardgameapp.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.data.dto.GameNight
import com.example.boardgameapp.ui.event.attendence.AttendenceDialogFragment
import com.example.boardgameapp.ui.event.foodStyles.FoodStylesDialogFragment
import com.example.boardgameapp.ui.event.hostrating.HostRatingDialog
import com.example.boardgameapp.ui.event.suggestGame.SuggestGamesDialogFragment
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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
        _binding = FragmentEventBinding.inflate(inflater, container, false)

        /**SetEventId in EventViewModel*/
        viewModel.setEventId(args.eventId)

        /**observe if there are already suggested Games for this event*/
        viewModel.retrieveEventGameNames(args.eventId).observe(this.viewLifecycleOwner) { names ->
            // if there are no suggested games yet, show a text
            if (names.isEmpty()) {
                binding.suggestedGames.text = ""
                // if there are already suggested games, add all suggested games to the display
            } else {
                binding.suggestedGames.text =
                    getString(R.string.suggested_games_1s, names).replace("[", "").replace("]", "")
            }
        }

        //TODO implement this
//        /**observe if there are already suggested Games for this event*/
//        viewModel.retrieveEventFoodNames(args.eventId).observe(this.viewLifecycleOwner) { foods ->
//            // if there are no suggested games yet, show a text
//            if (foods.isEmpty()) {
//                binding.suggestedGames.text = ""
//                // if there are already suggested games, add all suggested games to the display
//            } else {
//                binding.suggestedGames.text =
//                    getString(R.string.suggested_games_1s, foods).replace("[", "").replace("]", "")
//            }
//        }
        /*Navigation*/
        navController = findNavController()
        viewModel.setEventID(args.eventId)

        return binding.root
    }


    private fun bind(gameNight: GameNight) {
        binding.apply {
            date.text = getString(R.string.date, gameNight.date)
            host.text = getString(R.string.host_and_night_details, gameNight.host)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Retrieves an GameNight Object with the given eventId and hostIde
         **/
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
         * Opens the SuggestGameDialog
         */
        binding.suggestGameButton.setOnClickListener {
            SuggestGamesDialogFragment().show(
                (activity as AppCompatActivity).supportFragmentManager,
                "SuggestGameDialogFragment"
            )
        }

        /**
         * Navigates to the FoodStyles Fragment
         * */
        binding.selectAFoodstyle.setOnClickListener {
            FoodStylesDialogFragment(args.eventId).show(
                (activity as AppCompatActivity).supportFragmentManager,
                "FoodStyleDialogFragment"
            )
        }

        /**
         * Opens the AttendenceDialog
         * */
        binding.attendenceButton.setOnClickListener {
            AttendenceDialogFragment().show(
                (activity as AppCompatActivity).supportFragmentManager,
                "AttendenceDialogFragment"
            )
        }

        /**
         * Opens the DelayedDialog
         * */
        binding.delayedButton.setOnClickListener {
            DelayedDialogFragment().show(
                (activity as AppCompatActivity).supportFragmentManager, "DelayedDialogFragment"
            )
        }

        /**
         * Opens the HostRatingDialog
         * */
        binding.hostRatingButton.setOnClickListener {
            HostRatingDialog(gameNight.hostRating, gameNight.hostId).show(
                (activity as AppCompatActivity).supportFragmentManager,
                "HostRatingDialogFragment"
            )
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