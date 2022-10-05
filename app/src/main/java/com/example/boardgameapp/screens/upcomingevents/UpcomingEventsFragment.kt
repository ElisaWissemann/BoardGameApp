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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgameapp.R
import com.example.boardgameapp.databinding.FragmentEventBinding
import com.example.boardgameapp.databinding.FragmentUpcomingEventsBinding
import com.example.boardgameapp.databinding.UpcomingEventsItemViewBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class UpcomingEventsFragment : Fragment() {

    companion object {
        fun newInstance() = UpcomingEventsFragment()
    }

    private lateinit var binding: FragmentUpcomingEventsBinding
    private lateinit var viewModel: UpcomingEventsViewModel


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


}