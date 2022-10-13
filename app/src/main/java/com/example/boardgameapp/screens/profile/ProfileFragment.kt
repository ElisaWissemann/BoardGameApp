package com.example.boardgameapp.screens.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
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
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = UserDataSource.users
        val args: ProfileFragmentArgs by navArgs()

        //Log.i("Chris", args.toString())

        user = userData.find { it.id == args.pUserId }!!
        _binding!!.usernameText.text = getString(R.string.username, user.name)
        val name = user.name + " " + user.surname
        _binding!!.nameText.text = getString(R.string.name, name)
        _binding!!.adressText.text = getString(R.string.address, user.address)
        //val upcomingEventDate = users.hosting_events
        //_binding!!.eventText.text = getString(R.string.upcoming_hosting_event_text, upcomingEventDate)
        _binding!!.favoriteGameText.text = getString(R.string.favorite_game_text, user.favorite_game)
        _binding!!.favoriteFoodText.text = getString(R.string.favorite_food_text, user.favorite_food)
    }
    //TODO RatingAsHost und UpcomingHostingEvents
    //TODO ScrollView
    //TODO EditButton DeleteButton



}