package com.example.boardgameapp.ui.event.hostrating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.databinding.FragmentHostRatingDialogBinding
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.ui.event.EventViewModel
import com.example.boardgameapp.ui.event.EventViewModelFactory
import com.example.boardgameapp.data.usecases.FormatRatingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//TODO: get hostId and rating from eventId in the EventViewModel
class HostRatingDialog(private var ratingFromDatabase: ArrayList<Double>?,private var hostId: Int) : DialogFragment() {

    private var currentRating: Float? = null
    private var _binding: FragmentHostRatingDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EventViewModel by activityViewModels {
        EventViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Set a Background with rounded corners for the Dialog
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentHostRatingDialogBinding.bind(inflater.inflate(R.layout.fragment_host_rating_dialog, container, false))

        currentRating = FormatRatingUseCase(rating = ratingFromDatabase).getRating()
        binding.hrTotalRating.text = getString(R.string.total_rating_1d, currentRating.toString())

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        /**
         * start coroutine to update the rating in the user
         * */
        binding.hrSubmit.setOnClickListener {
            //execute the corouting on I/O Thread
            lifecycleScope.launch (Dispatchers.IO){
                viewModel.buildUserWithNewRating(binding.ratingBar.rating,hostId)

            }

            //dismiss dialog
            dialog?.dismiss()        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}