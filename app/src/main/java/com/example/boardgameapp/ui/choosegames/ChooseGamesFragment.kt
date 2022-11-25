
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
import com.example.boardgameapp.R
import com.example.boardgameapp.database.game.GameDataSource
import com.example.boardgameapp.databinding.FragmentChooseGamesBinding


class ChooseGamesFragment : Fragment(), OnItemSelectedListener {

    companion object {
        fun newInstance() = ChooseGamesFragment()
    }

    private var binding: FragmentChooseGamesBinding? = null
    private lateinit var viewModel: ChooseGamesViewModel
    var games : Array<String> = arrayOf()
    private lateinit var spinner1: Spinner

    private lateinit var spinner2: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_choose_games,
            container,
            false
        )

        spinner1 = binding!!.gSpinner1
        spinner1.onItemSelectedListener = this
        spinner2 = binding!!.gSpinner2
        spinner2.onItemSelectedListener = this
        games = loadGameData()

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            games
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        val ad2: ArrayAdapter<*> = ArrayAdapter<Any?>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            games
        )

        ad2.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner1.adapter = ad
        spinner2.adapter = ad2

        return binding!!.root
    }

    private fun loadGameData() = GameDataSource.games.map { it.name }.toTypedArray()


    override fun onItemSelected(p0: AdapterView<*>?, vw: View?, pos: Int, id: Long) {
        Toast.makeText(
            context,
            games[pos],
            Toast.LENGTH_LONG
        )
            .show()
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {}

}
