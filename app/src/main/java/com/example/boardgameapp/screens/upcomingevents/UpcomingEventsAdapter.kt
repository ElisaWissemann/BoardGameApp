package com.example.boardgameapp.screens.upcomingevents

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.user.UserDataSource
import com.example.boardgameapp.databinding.UpcomingEventsItemViewBinding


class UpcomingEventsAdapter() : RecyclerView.Adapter<UpcomingEventsAdapter.UpcomingEventsViewHolder>() {

    //Initialize Data
    private val eventData = EventDataSource.events
    private val userData = UserDataSource.users
    private lateinit var binding: UpcomingEventsItemViewBinding
    private lateinit var context: Context


    class UpcomingEventsViewHolder(val binding: UpcomingEventsItemViewBinding):RecyclerView.ViewHolder(binding.root) {
        val hostView = binding.ueHost
        val dateView = binding.ueDate
        val acceptedView = binding.ueAccepted
        val cancelledView = binding.ueCancelled
        val enterEventButton = binding.ueEnterEventButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingEventsViewHolder {
         binding = UpcomingEventsItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingEventsViewHolder(binding)
    }



    override fun getItemCount(): Int = eventData.size

    override fun onBindViewHolder(holder: UpcomingEventsViewHolder, position: Int) {
        val item = eventData[position]
        val hostData = userData.find { it.id == item.host }
        val hostName= hostData?.name + " " + hostData?.surname

        holder.hostView?.text = context.getString(R.string.hostet_by, hostName)
        holder.dateView?.text = item.date.toString()
        holder.acceptedView?.text = item.accepted.size.toString()
        holder.cancelledView?.text = item.cancelled.size.toString()
        holder.enterEventButton.setOnClickListener{

            val action = UpcomingEventsFragmentDirections.actionUpcomingEventsFragmentToEventFragment(eventId = item.id)
            holder.binding.root.findNavController().navigate(action)
        }


        }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

}