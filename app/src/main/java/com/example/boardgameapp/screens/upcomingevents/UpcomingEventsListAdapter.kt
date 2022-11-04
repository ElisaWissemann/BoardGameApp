/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.inventory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgameapp.R
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.entities.User
import com.example.boardgameapp.databinding.UpcomingEventsItemViewBinding
import com.example.boardgameapp.screens.upcomingevents.UpcomingEventsFragmentDirections


/**
 * [ListAdapter] implementation for the recyclerview.
 */

class UpcomingEventsListAdapter() :
    ListAdapter<Event, UpcomingEventsListAdapter.ItemViewHolder>(DiffCallback()) {
    private var _eventData: MutableList<Event>? = null
    private var _hostData: List<User>? = null
    private lateinit var context: Context
    private var binding: UpcomingEventsItemViewBinding? = null



    class ItemViewHolder(var binding: UpcomingEventsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            binding.apply {
//                holder.hostView.text = context.getString(R.string.hostet_by, hostName)
                ueHost.text = event.host.toString()
                ueDate.text = event.date
                ueAccepted.text = event.accepted?.size.toString()
                ueCancelled.text = event.cancelled?.size.toString()
                ueEnterEventButton.setOnClickListener {
                    val action =
                        UpcomingEventsFragmentDirections.actionUpcomingEventsFragmentToEventFragment(
                            eventId = event.id
                        )
                    root.findNavController().navigate(action)
                }
            }
        }
    }


    class DiffCallback: DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            UpcomingEventsItemViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        binding = null
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }


    fun setEventData(eventData: MutableList<Event>){
        _eventData = eventData
        this.notifyDataSetChanged()
    }
    fun setHostData(hostData: List<User>){
        _hostData = hostData
        this.notifyDataSetChanged()
    }


//    companion object {
//        private val DiffCallback = object {
//            fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
//                return oldItem === newItem
//            }
//            fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
}