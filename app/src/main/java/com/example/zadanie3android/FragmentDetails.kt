package com.example.zadanie3android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.zadanie3android.databinding.FragmentDetailsBinding
import com.example.zadanie3android.databinding.FragmentListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var myRepository: MyRepository
    lateinit var adapter : FragmentList.MyAdapter
    private var selectedItemPosition: Int = -1

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
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        myRepository = MyRepository.getinstance(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedItemPosition = arguments?.getInt("selectedItemPosition", -1)

            var selectedItem = selectedItemPosition?.let { myRepository.getData()?.get(it) }

            if (selectedItem != null) {
                binding.tv1Add.text = selectedItem.text_main

                binding.tv2Add.text = selectedItem.text_sport

                binding.tvOpis.text = selectedItem.item_opis

                binding.ratingBar.rating = selectedItem.item_trudnosc.toFloat()
            }

                // Set up Modify button click listener
                binding.btnModify.setOnClickListener {
                    val bundle = Bundle().apply {

                        if (selectedItemPosition != null) {
                            putInt("selectedItemPosition",selectedItemPosition)
                        }
                        // Dodaj inne dane, które chcesz przekazać
                    }
                    view?.let { Navigation.findNavController(it).navigate(R.id.action_details_to_add,bundle) }

                }

//            }
//        }

        val backButton: Button = (requireActivity().findViewById<View>(R.id.button) as Button)
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
         * @return A new instance of fragment FragmentDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}