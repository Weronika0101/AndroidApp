package com.example.zadanie3android

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
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

    private val sharedViewModel: SharedViewModel by viewModels()


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

        val inputSport = binding.inputDyscyplina
        val inputPoczatkujacy = binding.inputPoczatkujacy
        val inputTrudnosc = binding.inputTrudnosc
        val inputOpis = binding.inputOpis
        val selectedItemPositionA = arguments?.getInt("selectedItemPosition", -1)
        if(selectedItemPositionA ==-1) {
            val data = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            myRepository = MyRepository.getinstance(requireContext())
            val id = data.getLong("id", 0)
            val dataItem = myRepository.getDataById(id)
            if (dataItem != null) {
                if(dataItem.text_sport!="") inputSport.setText(dataItem.text_sport)
                else inputSport.setText("Tenis")

                if(dataItem.item_beginner==true) inputPoczatkujacy.check(R.id.radioButton3)
                else inputPoczatkujacy.check(R.id.radioButton4)
                inputTrudnosc.rating=dataItem.item_trudnosc.toFloat()
                inputOpis.setText(dataItem.item_opis)


            }
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputSport = binding.inputDyscyplina
        val inputPoczatkujacy = binding.inputPoczatkujacy.checkedRadioButtonId
        val inputTrudnosc = binding.inputTrudnosc
        val inputOpis = binding.inputOpis
        val selectedItemPositionA = arguments?.getInt("selectedItemPosition", -1)
        myRepository = MyRepository.getinstance(requireContext())

        val btnSave: Button = view.findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            if(selectedItemPositionA ==-1) {
                val data = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
                val id = data.getLong("id", 0)
                val dataItem = myRepository.getDataById(id)
                if(dataItem!=null) {
                    myRepository.deleteItem(dataItem)
                }
            val sport = inputSport.text.toString().takeIf { it.isNotEmpty() } ?: ""
            val poczatkujacy = binding.radioButton3.isChecked
            val trudnosc = inputTrudnosc.rating.toInt()
            val opis = inputOpis.text.toString().takeIf { it.isNotEmpty() } ?: ""
            val typ: Int
            if (sport == "Gimnastyka") {
                typ = 0
            } else if (sport == "Piłka ręczna") {
                typ = 1
            } else if (sport == "Koszykówka") {
                typ = 2
            } else if (sport == "Tenis") {
                typ = 3
            } else {
                typ = 3
            }

            myRepository.addItem(DBItem(sport, poczatkujacy, trudnosc, opis, typ))
            //findNavController().navigate(R.id.listFragment)
                view.let { Navigation.findNavController(it).navigate(R.id.action_add_to_new,Bundle.EMPTY) }
        } else {

                val data = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
                val id = data.getLong("id", 0)
                val dataItem = myRepository.getDataById(id)
                if(dataItem!=null) {
                    myRepository.deleteItem(dataItem)
                }


                val sport = inputSport.text.toString().takeIf { it.isNotEmpty() } ?: ""
                val poczatkujacy = binding.radioButton3.isChecked
                val trudnosc = inputTrudnosc.rating.toInt()
                val opis = inputOpis.text.toString().takeIf { it.isNotEmpty() } ?: ""
                val typ: Int
                if (sport == "Gimnastyka") {
                    typ = 0
                } else if (sport == "Piłka ręczna") {
                    typ = 1
                } else if (sport == "Koszykówka") {
                    typ = 2
                } else if (sport == "Tenis") {
                    typ = 3
                } else {
                    typ = 3
                }

                myRepository.addItem(DBItem(sport, poczatkujacy, trudnosc, opis, typ))
                //findNavController().navigate(R.id.listFragment)
                view.let { Navigation.findNavController(it).navigate(R.id.action_add_to_new,Bundle.EMPTY) }
            }

        }
        val exitButton: Button =(requireActivity().findViewById<View>(R.id.btnAnuluj) as Button)
        exitButton.setOnClickListener {
            requireActivity().onBackPressed()
            //findNavController().navigate(R.id.listFragment)
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