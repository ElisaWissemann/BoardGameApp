package com.example.boardgameapp.screens.upcomingevents

import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgameapp.databinding.FragmentUpcomingEventsBinding


class UpcomingEventsFragment : Fragment() {

    companion object {
        fun newInstance() = UpcomingEventsFragment()
    }

    lateinit var binding: FragmentUpcomingEventsBinding
    lateinit var viewModel: UpcomingEventsViewModel
    private lateinit var eventData: List<CalendarContract.Events>
    var adapter = UpcomingEventsAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*DataBinding*/
        binding = FragmentUpcomingEventsBinding
            .inflate(inflater, container, false)

        /*ViewModel*/
        viewModel = ViewModelProvider(this).get(UpcomingEventsViewModel::class.java)
        binding.upcomingEventsViewModel = viewModel
        binding.lifecycleOwner = this

        //return view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //create recyclerView
        var recyclerView: RecyclerView = binding.verticalRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        //trigger adapter to get Data from ViewModel when it is available
        viewModel.eventData.observe(viewLifecycleOwner, Observer{ data ->
            adapter.setEventData(data)
        })
        viewModel.hostData.observe(viewLifecycleOwner, Observer{ data ->
            adapter.setHostData(data)
        })
        recyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        //setTitle in ActionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Next"
    }

}