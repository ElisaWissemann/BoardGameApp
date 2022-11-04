package com.example.boardgameapp.screens.upcomingevents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.databinding.FragmentUpcomingEventsBinding
import com.example.inventory.UpcomingEventsListAdapter


class UpcomingEventsFragment : Fragment() {

    //TODO: Change Adapter to ListAdapter for automatical updates if data from database changes
    //see: https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-5-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-intro-room-flow#8

    companion object {
        fun newInstance() = UpcomingEventsFragment()
    }

    private val viewModel: UpcomingEventsViewModel by activityViewModels {
        UpcomingEventsViewModelFactory(

//        UpcomingEventsItemViewModelFactory(
            (activity?.application as BoardGameApplication).database.boardGameDao
        )
    }
    private var _binding: FragmentUpcomingEventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /*DataBinding*/
            _binding = FragmentUpcomingEventsBinding
                .inflate(inflater, container, false)
        //return view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UpcomingEventsListAdapter()
        //create recyclerView
        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.verticalRecyclerView.adapter = adapter

        viewModel.eventData.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //setTitle in ActionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Next"
    }

}