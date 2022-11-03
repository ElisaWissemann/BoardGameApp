package com.example.boardgameapp.screens.event.hostrating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.boardgameapp.R
import com.example.boardgameapp.database.BoardGameDatabase
import com.example.boardgameapp.database.BoardGameRepository
import com.example.boardgameapp.database.FormatRatingUseCase
import com.example.boardgameapp.databinding.FragmentHostRatingDialogBinding

//TODO: Add Comments
class HostRatingDialog(private var rating: ArrayList<Double>?) : DialogFragment() {

    private var _binding: FragmentHostRatingDialogBinding? = null
    private var currentRating: Float? = null

    //This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Set a Background with rounded corners for the Dialog
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        //inflate the Layout
        val view = inflater.inflate(R.layout.fragment_host_rating_dialog, container, false)
        //ViewBinding
        _binding = FragmentHostRatingDialogBinding.bind(view)
        // get the ratingBar reference
        val ratingBar = _binding!!.ratingBar


        // TODO: make it MVVM conform
        //get the current rating of the host from the DB
        if (rating != null) {
            currentRating = FormatRatingUseCase(rating = rating).getRating()
        } else {
            //TODO: Set initial Value for Rating to 0 and make it nonNullable
            currentRating = 0.00f
        }

        binding.hrTotalRating.text = getString(R.string.total_rating_1d, currentRating.toString())

        //submit button clickListener to send the vote to the DB
        binding.hrSubmit.setOnClickListener {
            //TODO: add the rating to the user Object
            // get number of stars the user tabed
            //val vote = ratingBar.rating
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(rating: ArrayList<Double>) =
            HostRatingDialog(rating).apply {

            }
    }

}