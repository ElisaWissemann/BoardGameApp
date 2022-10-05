package com.example.boardgameapp.screens.food

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.boardgameapp.R

class FoodFragment : Fragment() {

    companion object {
        fun newInstance() = FoodFragment()
    }

    private lateinit var viewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val s = "test"
        return inflater.inflate(R.layout.fragment_food, container, false)
    }


}