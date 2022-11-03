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
import com.example.boardgameapp.database.BoardGameDatabase
import com.example.boardgameapp.database.BoardGameRepository
import com.example.boardgameapp.databinding.FragmentUpcomingEventsBinding


class UpcomingEventsFragment : Fragment() {

    //TODO: Change Adapter to ListAdapter for automatical updates if data from database changes
    //see: https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-5-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-intro-room-flow#8

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

            /*DB*/
            val db = BoardGameDatabase
            val dao = db.getInstance(requireActivity().application).boardGameDao
            val repository = BoardGameRepository(dao)

            /*ViewModel*/
            val factory = UpcomingEventViewModelFactory(repository)
            viewModel = ViewModelProvider(this, factory).get(UpcomingEventsViewModel::class.java)
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
        recyclerView.adapter = adapter


        //trigger adapter to get Data from ViewModel when it is available
        viewModel.eventData.observe(viewLifecycleOwner, Observer{ data ->
            adapter.setEventData(data)
        })
        viewModel.hostData.observe(viewLifecycleOwner, Observer{ data ->
            adapter.setHostData(data)
        })

    }

    override fun onStart() {
        super.onStart()
        //setTitle in ActionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Next"
    }

}