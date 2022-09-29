package com.example.boardgameapp.screens.choosegames

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.boardgameapp.R

class ChooseGamesFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseGamesFragment()
    }

    private lateinit var viewModel: ChooseGamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_games, container, false)
    }


}