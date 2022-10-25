package com.example.boardgameapp.screens.upcomingevents

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgameapp.R
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.databinding.UpcomingEventsItemViewBinding


class UpcomingEventsAdapter :
    RecyclerView.Adapter<UpcomingEventsAdapter.UpcomingEventsViewHolder>() {

    //Initialize Data
    ////TODO: Move this to the ViewModel and make it MVVM conform
    private var _eventData: List<Event>? = null
    private var _hostData: List<User>? = null
    //binding for upcoming_events_item.xml
    private var binding: UpcomingEventsItemViewBinding? = null
    private lateinit var context: Context


    class UpcomingEventsViewHolder(val binding: UpcomingEventsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val hostView = binding.ueHost
        val dateView = binding.ueDate
        val acceptedView = binding.ueAccepted
        val cancelledView = binding.ueCancelled
        val enterEventButton = binding.ueEnterEventButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingEventsViewHolder {
        binding = UpcomingEventsItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return UpcomingEventsViewHolder(binding!!)
    }

    override fun getItemCount(): Int = _eventData!!.size

    override fun onBindViewHolder(holder: UpcomingEventsViewHolder, position: Int) {
        val item = _eventData!![position]
        val hostData = _hostData?.find { it.id == item.host }
        val hostName = hostData?.name + " " + hostData?.surname

        holder.hostView.text = context.getString(R.string.hostet_by, hostName)
        holder.dateView.text = item.date
        holder.acceptedView.text = item.accepted.size.toString()
        holder.cancelledView.text = item.cancelled.size.toString()
        holder.enterEventButton.setOnClickListener {

            val action =
                UpcomingEventsFragmentDirections.actionUpcomingEventsFragmentToEventFragment(eventId = item.id)
            holder.binding.root.findNavController().navigate(action)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        binding = null
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    fun setEventData(eventData: List<Event>){
        _eventData = eventData
        this.notifyDataSetChanged()
    }
     fun setHostData(hostData: List<User>){
         _hostData = hostData
         this.notifyDataSetChanged()
     }
}