package com.example.boardgameapp.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        _binding = FragmentDelayedDialogBinding.bind(inflater.inflate(R.layout.fragment_delayed_dialog, container, false))

        //Creating minutes delayed Btn's
        binding.del15min.setOnClickListener {
            Toast.makeText(context, "The other players have been notified about your delay of 15 min", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        //Cancel Dialog Btn
        binding.del30min.setOnClickListener {
            Toast.makeText(context, "The other players have been notified about your delay of 30 min", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        //Cancel Dialog Btn
        binding.del45min.setOnClickListener {
            Toast.makeText(context, "The other players have been notified about your delay of 45 min", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        //Cancel Dialog Btn
        binding.del1h.setOnClickListener {
            Toast.makeText(context, "The other players have been notified about your delay of 60 min", Toast.LENGTH_LONG).show()
            dialog!!.dismiss()
        }

        //RefuseBtn
        binding.delRefuseBtn.setOnClickListener {
            val UserID = userId
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

