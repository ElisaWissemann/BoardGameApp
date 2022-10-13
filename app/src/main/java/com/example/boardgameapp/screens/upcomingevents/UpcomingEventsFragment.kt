package com.example.boardgameapp.screens.upcomingevents

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgameapp.R
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.databinding.FragmentUpcomingEventsBinding

class UpcomingEventsFragment : Fragment() {

    companion object {
        fun newInstance() = UpcomingEventsFragment()
    }

    lateinit var binding: FragmentUpcomingEventsBinding
    lateinit var viewModel: UpcomingEventsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingEventsBinding
            .inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerView: RecyclerView = binding.verticalRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = UpcomingEventsAdapter()

    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Next"

    }


}