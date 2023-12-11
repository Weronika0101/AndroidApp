package com.example.zadanie3android
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie3android.databinding.FragmentTab1Binding
import com.example.zadanie3android.databinding.FragmentTab2Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class FragmentTab2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentTab2Binding
    private lateinit var sharedViewModel: SharedViewModel

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
        binding = FragmentTab2Binding.inflate(inflater, container, false)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        binding.choose.setOnClickListener {
            // Tutaj można otworzyć DialogFragment lub inny sposób wybierania obrazka
            // Na potrzeby przykładu, ustawiamy obrazek o ID R.drawable.flower1
            sharedViewModel.setSelectedImage(R.drawable.flower2)
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTab2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView4.setOnClickListener {
            // Tutaj można otworzyć DialogFragment lub inny sposób wybierania obrazka
            // Na potrzeby przykładu, ustawiamy obrazek o ID R.drawable.flower1
            sharedViewModel.setSelectedImage(R.drawable.flower1)
        }
//        view.findViewById<View>(R.id.btnfragment1).setOnClickListener{_->
//            var value = view.findViewById<RatingBar>(R.id.rbfragment1).rating
//            parentFragmentManager.setFragmentResult("msgfromchild", bundleOf("msg1" to ("value from child = " + value)))
//        }

    }
}