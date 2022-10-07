package com.example.boardgameapp.screens.profile

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.R
import com.example.boardgameapp.R.id.textView9
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.screens.event.EventFragmentArgs

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }
    private lateinit var binding: FragmentEventBinding

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }





}