package com.example.zadanie3android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zadanie3android.databinding.FragmentSwipeBinding
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSwipe.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSwipe : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var swipeBinding: FragmentSwipeBinding

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
        swipeBinding = FragmentSwipeBinding.inflate(inflater, container, false)
        return swipeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = MyPagerAdapter(requireActivity())

        // Set up ViewPager2
        swipeBinding.viewPager2.adapter = pagerAdapter

        // Set up TabLayout with ViewPager2 using TabLayoutMediator
        TabLayoutMediator(swipeBinding.tabLayout, swipeBinding.viewPager2) { tab, position ->
            tab.text = "Tab ${(position + 1)}"
            // tab.setIcon(tabIcons[0])
            // Customize the tab text or icons here
//            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentSwipe.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSwipe().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}