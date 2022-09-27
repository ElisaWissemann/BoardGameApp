package com.example.boardgameapp.screens.attendence

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.boardgameapp.R

class AttendenceFragment : Fragment() {

    companion object {
        fun newInstance() = AttendenceFragment()
    }

    private lateinit var viewModel: AttendenceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attendence, container, false)
    }
}