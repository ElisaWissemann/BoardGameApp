package com.example.boardgameapp.ui.game

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
import com.example.boardgameapp.R
import com.example.boardgameapp.database.game.GameDataSource
import com.example.boardgameapp.databinding.FragmentGameBinding

class GameFragment : Fragment(), OnItemSelectedListener {

    companion object {
        fun newInstance() = GameFragment()
    }

    private var binding: FragmentGameBinding? = null
    private lateinit var viewModel: GameViewModel
    var games : Array<String> = arrayOf()
    var courses = arrayOf<String?>(
        "C", "Data structures",
        "Interview prep", "Algorithms",
        "DSA with java", "OS"
    )
    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        spinner = binding!!.gSpinner1
        spinner.onItemSelectedListener = this
        loadGameData()

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            courses
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.adapter = ad


        //setupSpinner1()
        return binding!!.root
    }

    private fun loadGameData() {
        var gamesDataSource = GameDataSource.games
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(
            context,
            courses[1],
            Toast.LENGTH_LONG
        )
            .show()
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {}

}