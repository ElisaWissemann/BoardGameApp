package com.example.boardgameapp.ui.foodstyles

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
import androidx.navigation.fragment.navArgs
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.databinding.FragmentFoodBinding


class FoodStylesFragment : Fragment(), OnItemSelectedListener {

    companion object {
        fun newInstance() = FoodStylesFragment()
    }

    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!

    private val args: FoodStylesFragmentArgs by navArgs()  // TODO Bodo delete?

    private val viewModel: FoodStylesViewModel by activityViewModels {
        FoodStylesViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }

    var foodStyles = arrayOf<String>()
    private lateinit var spinner: Spinner
    private var selectedItem1 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = binding.foodSpinner
        spinner.onItemSelectedListener = this

        viewModel.foodStyles.observe(this.viewLifecycleOwner) {
            foodStyles = it

            val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                foodStyles
            )

            adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )

            spinner.adapter = adapter
        }

        binding.foodConfirm.setOnClickListener {
            if (selectedItem1 !== "") {
                //TODO: add selected game to DB
            } else {
                Toast.makeText(context, "please select a foodstyle", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, vw: View?, pos: Int, id: Long) {
        selectedItem1 = foodStyles[pos]
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {}

}