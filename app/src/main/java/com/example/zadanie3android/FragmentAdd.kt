package com.example.zadanie3android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RatingBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.zadanie3android.databinding.FragmentAddBinding
import com.example.zadanie3android.databinding.FragmentDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAdd.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAdd : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myRepository: MyRepository
    private lateinit var binding: FragmentAddBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        myRepository = MyRepository.getinstance(requireContext())
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedItemPosition = arguments?.getInt("selectedItemPosition") ?: -1
        if (selectedItemPosition != -1) {
            val selectedItem = selectedItemPosition.let { myRepository.getData()?.get(it) }
            if (selectedItem != null) {
                // Update UI with details
                binding.inputDyscyplina.setText(selectedItem.text_sport)
                binding.inputOpis.setText(selectedItem.item_opis)
                binding.inputTrudnosc.rating = selectedItem.item_trudnosc.toFloat()
                // Update other UI elements accordingly

                val beginnerRadioButtonId =
                    if (selectedItem.item_beginner) R.id.radioButton3 else R.id.radioButton4
                binding.inputPoczatkujacy.check(beginnerRadioButtonId)
            }
        }


                val btnSave: Button = view.findViewById(R.id.btnSave)
                btnSave.setOnClickListener { view: View ->
                    if (selectedItemPosition == -1) {

                        var text_sport =
                            view.findViewById<EditText>(R.id.input_dyscyplina)?.text.toString()
                        var inputTrudnoscRatingBar =
                            view.findViewById<RatingBar>(R.id.input_trudnosc)
                        //var item_trudnosc = inputTrudnoscRatingBar.rating.toInt()
                        var item_trudnosc = 3
                        var item_opis =
                            view.findViewById<EditText>(R.id.input_opis)?.text.toString()
                        var item_beginner =
                            view.findViewById<RadioGroup>(R.id.input_poczatkujacy)?.checkedRadioButtonId == R.id.radioButton3
                        if (text_sport == "Gimnastyka") {
                            var item_type = 0
                        } else if (text_sport == "Piłka ręczna") {
                            var item_type = 1
                        } else if (text_sport == "Koszykówka") {
                            var item_type = 2
                        } else if (text_sport == "Tenis") {
                            var item_type = 3
                        } else {
                            var item_type = 3
                        }

//                        Log.d(
//                            "TAG",
//                            item.text_sport + item.item_opis + item.item_beginner + item.item_type
//                        )

                        if (MyRepository.getinstance(requireContext()).addItem(DBItem(text_sport,item_beginner, item_trudnosc ,item_opis)))
                            parentFragmentManager.setFragmentResult("item_added", Bundle.EMPTY)

                        requireActivity().onBackPressed()

                    } else {
                        var selectedItem =
                            selectedItemPosition.let { myRepository.getData()?.get(it) }
                        if (selectedItem != null) {
                            selectedItem.text_sport = binding.inputDyscyplina.text.toString()
                            selectedItem.item_opis = binding.inputOpis.text.toString()
                            var inputTrudnoscRatingBar =
                                view.findViewById<RatingBar>(R.id.input_trudnosc)
                            if (inputTrudnoscRatingBar != null) {
                                selectedItem.item_trudnosc = inputTrudnoscRatingBar.rating.toInt()
                            }
                            selectedItem.item_beginner =
                                view.findViewById<RadioGroup>(R.id.input_poczatkujacy)?.checkedRadioButtonId == R.id.radioButton3
                            if (selectedItem.text_sport == "Gimnastyka") {
                                selectedItem.item_type = 0
                            } else if (selectedItem.text_sport == "Piłka ręczna") {
                                selectedItem.item_type = 1
                            } else if (selectedItem.text_sport == "Koszykówka") {
                                selectedItem.item_type = 2
                            } else if (selectedItem.text_sport == "Tenis") {
                                selectedItem.item_type = 3
                            } else {
                                selectedItem.item_type = 3
                            }
                        }
                        if (selectedItem != null) {
                            myRepository.updateItem(selectedItem)
                        }


                        requireActivity().onBackPressed()

                    }
                }

                val backButton: Button =
                    (requireActivity().findViewById<View>(R.id.btnAnuluj) as Button)
                backButton.setOnClickListener { _ ->
                    requireActivity().onBackPressed()
                }

            }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentAdd.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAdd().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}