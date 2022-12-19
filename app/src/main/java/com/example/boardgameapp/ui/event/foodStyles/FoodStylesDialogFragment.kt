package com.example.boardgameapp.ui.event.foodStyles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.boardgameapp.R
import com.example.boardgameapp.databinding.FragmentFoodStylesDialogBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.boardgameapp.BoardGameApplication
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.ui.foodstyles.FoodStylesViewModel
import com.example.boardgameapp.ui.foodstyles.FoodStylesViewModelFactory


/**
 * A simple [DialogFragment] subclass.
 */
class FoodStylesDialogFragment (private var eventId:Int)  : DialogFragment(),
    AdapterView.OnItemSelectedListener {
    private var _binding: FragmentFoodStylesDialogBinding? = null

    //This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    private val viewModel: FoodStylesViewModel by activityViewModels {
        FoodStylesViewModelFactory(
            BoardGameRepository((activity?.application as BoardGameApplication).database.boardGameDao)
        )
    }

    var foodStyles = arrayOf<String>()
    private lateinit var spinner: Spinner
    private var selectedItem1 = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Set a Background with rounded corners for the Dialog
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentFoodStylesDialogBinding.bind(inflater.inflate(R.layout.fragment_food_styles_dialog, container, false))

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