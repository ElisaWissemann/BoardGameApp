package com.example.boardgameapp.ui.upcomingevents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.databinding.FragmentUpcomingEventsBinding
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.inventory.UpcomingEventsListAdapter


class UpcomingEventsFragment : Fragment() {

    companion object {
        fun newInstance() = UpcomingEventsFragment()
    }

    private val viewModel: UpcomingEventsViewModel by activityViewModels {
        UpcomingEventsViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao))
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


        viewModel.upcomingGameNights.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }

        //TODO: implementation
        binding.floatingActionButton.setOnClickListener {
            //viewModel.addNewEvent()
            Toast.makeText(getView()?.context, "Coming Soon", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        //setTitle in ActionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Next"
    }

}