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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.R
import com.example.boardgameapp.databinding.FragmentChooseGamesBinding
import com.example.boardgameapp.repositories.BoardGameRepository


class ChooseGamesFragment : Fragment(), OnItemSelectedListener {

    companion object {
        fun newInstance() = ChooseGamesFragment()
    }

    private var _binding: FragmentChooseGamesBinding? = null
    private val binding get() = _binding!!

    private val args: ChooseGamesFragmentArgs by navArgs()

    private val viewModel: ChooseGamesViewModel by activityViewModels {
        ChooseGamesViewModelFactory(
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

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_choose_games,
            container,
            false
        )
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
            val ad2: ArrayAdapter<*> = ArrayAdapter<Any?>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                games
            )

            spinner.adapter = adapter
        }

        binding.chGConfirm.setOnClickListener {
            if(selectedItem1 !== ""){
                //TODO: add selected game to DB
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
