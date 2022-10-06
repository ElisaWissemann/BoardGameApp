package com.example.boardgameapp.screens.event.hostrating

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.boardgameapp.R
import com.example.boardgameapp.data.user.FormatRatingUseCase
import com.example.boardgameapp.databinding.FragmentHostRatingDialogBinding

//TODO: Add Comments
class HostRatingDialog (userId: Int) : DialogFragment() {

    private var _binding: FragmentHostRatingDialogBinding? = null
    //This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    private var userId = userId


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Set a Background with rounded corners for the Dialog
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        //inflate the Layout
        val view = inflater.inflate(R.layout.fragment_host_rating_dialog, container, false)

        //ViewBinding
        _binding = FragmentHostRatingDialogBinding.bind(view)
        var ratingBar = _binding!!.ratingBar

        // get User rating
        var currentRating = FormatRatingUseCase(userId = userId).getRating()
        ratingBar.rating = currentRating.toFloat()
        //RatingBar
        //Code to save the RatingBar State
        //var number_of_stars = ratingbar.setOnRatingBarChangeListener { ratingbar, fl, b ->  Log.i("ELISA", ratingbar.rating.toString())}

        //TODO: Add Rating to the UserDataSource Object
        _binding!!.hrSubmit.setOnClickListener{
            Log.i("ELISA", ratingBar.rating.toString())
        }

        return view

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            //TODO: Fix this constructor
            ////!!!!!!!!FIX constructors
            HostRatingDialog(userId = 1).apply {

            }
    }

}