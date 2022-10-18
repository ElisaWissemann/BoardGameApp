package com.example.boardgameapp.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.user.FormatRatingUseCase
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource
import com.example.boardgameapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: User
    private lateinit var date: Event
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = UserDataSource.users
        val args: ProfileFragmentArgs by navArgs()
        user = userData.find { it.id == args.pUserId }!!
        _binding!!.usernameText.text = getString(R.string.username, user.name)
        val name = user.name + " " + user.surname
        _binding!!.nameText.text = getString(R.string.name, name)
        _binding!!.adressText.text = getString(R.string.address, user.address)

        _binding!!.favoriteGameText.text = getString(R.string.favorite_game_text, user.favorite_game)
        _binding!!.favoriteFoodText.text = getString(R.string.favorite_food_text, user.favorite_food)

        //RatingBar
        val averageRating = FormatRatingUseCase(userId = args.pUserId ).getRating()
        binding.totalRatingBar.setRating(averageRating)
        binding.totalRatingText.text = getString(R.string.total_rating_1d, averageRating.toString())
        //Upcoming Hosting Event
        val eventData = EventDataSource.events
        date = eventData.find { it.id == args.pUserId }!!
        _binding!!.eventText.text = getString(R.string.upcoming_hosting_event_text, date.date)

    }

//    override fun onStart() {
//        super.onStart()
//        //TODO Delete Profile Button
//        binding!!.deleteProfileButton.setOnClickListener {
//            val action =
//        }
//        //TODO Edit Profile Button
//        binding!!.editProfileButton.setOnClickListener {
//            val action =
//        }
//    }











}