package com.example.boardgameapp.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.boardgameapp.R
import com.example.boardgameapp.databinding.FragmentDelayedDialogBinding


class DelayedDialogFragment(private var userId: Int) : DialogFragment() {

    private var _binding: FragmentDelayedDialogBinding? = null

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
        val view = inflater.inflate(R.layout.fragment_delayed_dialog, container, false)
        //ViewBinding
        _binding = FragmentDelayedDialogBinding.bind(view)

        //Creating minutes delayed Btn's
        binding.del15min.setOnClickListener {
            Log.e("Anna", "15min")
        }

        //Cancel Dialog Btn
        binding.del30min.setOnClickListener {
            Log.e("Anna", "30min")
        }

        //Cancel Dialog Btn
        binding.del45min.setOnClickListener {
            Log.e("Anna", "45min")
        }

        //Cancel Dialog Btn
        binding.del1h.setOnClickListener {
            Log.e("Anna", "1h")
        }

        //Cancel Dialog Btn
        binding.delCloseBtn.setOnClickListener {
            dialog!!.dismiss()
        }

        //RefuseBtn
        binding.delRefuseBtn.setOnClickListener {
            val UserID = userId
            Log.e("Anna", "User" + UserID)
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
}

