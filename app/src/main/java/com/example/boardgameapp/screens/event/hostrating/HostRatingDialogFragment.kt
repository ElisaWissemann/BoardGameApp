package com.example.boardgameapp.screens.event.hostrating

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.boardgameapp.R


/**
 * A simple [Fragment] subclass.
 * Use the [HostRatingDialog.newInstance] factory method to
 * create an instance of this fragment.
 */
class HostRatingDialog : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.fragment_host_rating_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HostRatingDialog().apply {

            }
    }

}