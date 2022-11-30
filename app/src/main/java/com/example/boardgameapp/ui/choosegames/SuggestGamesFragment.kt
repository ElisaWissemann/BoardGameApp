package com.example.boardgameapp.ui.choosegames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.data.repositories.EventGameCrossRepository
import com.example.boardgameapp.databinding.FragmentSuggestGamesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SuggestGamesFragment : Fragment(), OnItemSelectedListener {

    companion object {
        fun newInstance() = SuggestGamesFragment()
    }

    private var _binding: FragmentSuggestGamesBinding? = null
    private val binding get() = _binding!!

    private val args: SuggestGamesFragmentArgs by navArgs()

    private val viewModel: SuggestGamesViewModel by activityViewModels {
        SuggestGamesViewModelFactory(
            EventGameCrossRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }

    var games = arrayOf<String>()
    private lateinit var spinner: Spinner
    private var selectedItem1 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSuggestGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = binding.chGSpinner
        spinner.onItemSelectedListener = this

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

        binding.chGConfirm.setOnClickListener {
            if(selectedItem1 !== ""){
                lifecycleScope.launch( Dispatchers.IO){
                    viewModel.updateEventWithSelectedGame(selectedItem1, args.eventId)
                }

            }else{
                Toast.makeText(context, "please select a game",Toast.LENGTH_LONG).show()
        }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, vw: View?, pos: Int, id: Long) {
      selectedItem1 = games[pos]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}
