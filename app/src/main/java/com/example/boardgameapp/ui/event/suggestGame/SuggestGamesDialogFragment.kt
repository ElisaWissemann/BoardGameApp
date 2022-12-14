package com.example.boardgameapp.ui.event.suggestGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.databinding.FragmentSuggestGameDialogBinding
import com.example.boardgameapp.ui.event.EventViewModel
import com.example.boardgameapp.ui.event.EventViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SuggestGamesDialogFragment() : DialogFragment(),
    OnItemSelectedListener {

    private var _binding: FragmentSuggestGameDialogBinding? = null
    private val binding get() = _binding!!
    private var eventId: Int = 0

    /**
     * Get instance of EventViewModel
     **/
    private val eventViewModel: EventViewModel by activityViewModels {
        EventViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }
    /**
     * Get instance of SuggestGamesDialogViewModel
     **/
    private val viewModel: SuggestGamesDialogViewModel by activityViewModels {
        SuggestGamesDialogViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }

    var games = arrayOf<String>()
    private lateinit var spinner: Spinner
    private var selectedItem1 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentSuggestGameDialogBinding.inflate(inflater, container, false)

        //Get Current EventId from StateFlow of EventViewModel
        lifecycleScope.launch {
            eventViewModel.eventId.collectLatest {
                eventId = it
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = binding.chGSpinner
        spinner.onItemSelectedListener = this

        /**
         * Get List of Games and retrieve to spinner
         * */
        viewModel.games.observe(this.viewLifecycleOwner) {
            games = it
            val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                games
            )
            adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            spinner.adapter = adapter
        }

        /**
         * Logic to confirm games selection*/
        binding.chGConfirm.setOnClickListener {
            if (selectedItem1 !== "") {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.updateEventWithSelectedGame(selectedItem1, eventId)
                }
                dialog!!.dismiss()
            } else {
                Toast.makeText(context, "please select a game", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, vw: View?, pos: Int, id: Long) {
        selectedItem1 = games[pos]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(context, "please select a game", Toast.LENGTH_LONG).show()
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