package com.example.zadanie3android

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie3android.databinding.FragmentListBinding
import com.example.zadanie3android.databinding.ListRowBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentList.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //    private lateinit var dataRepo: DataRepo
    lateinit var dataRepo: MyRepository
    lateinit var adapter : MyAdapter
    private lateinit var listbinding: FragmentListBinding

    val myvm : MyViewModel by activityViewModels {MyViewModel.Factory}

    var onItemAction : (item:DBItem, action:Int)-> Unit = { item, action ->
        when (action) {
            1-> {
                val data = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
                data.edit().putLong("id", item.id.toLong()).apply()
                findNavController().navigate(R.id.fragmentDetails)
            }//onlcick navigate do details
            2-> {
            dataRepo.deleteItem(item)
            adapter.submitList(dataRepo.getData())
            }

        }

    }


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
        listbinding = FragmentListBinding.inflate(inflater, container, false)
        dataRepo = MyRepository.getinstance(requireContext())
        adapter = MyAdapter(onItemAction)
        adapter.submitList(dataRepo.getData())

//        parentFragmentManager.setFragmentResultListener("item_added", this) {
//                requestKey, _ ->
//            adapter.data = dataRepo.getData()!!
//            adapter.notifyDataSetChanged()
//        }
        parentFragmentManager.setFragmentResultListener("item_added", this) { _, _ ->
            adapter.submitList(dataRepo.getData())
            // Item added, refresh the data
            //adapter.data = dataRepo.getData()!!
            //adapter.notifyDataSetChanged()
        }

        parentFragmentManager.setFragmentResultListener("item_modified", this) { _, _ ->
            adapter.submitList(dataRepo.getData())
            // Item modified, refresh the data
            //adapter.data = dataRepo.getData()!!
           // adapter.notifyDataSetChanged()
        }


        return listbinding.root
    }

    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dataRepo = DataRepo.getinstance()
//        val adapter = MyAdapter(dataRepo.getData())
//        val adapter = DataRepo.getinstance().getData()?.let { MyAdapter(it) }
//        val recView = listbinding.myRecView
//        recView.adapter = adapter
//        recView.layoutManager = LinearLayoutManager(requireContext())


        val recView = listbinding.myRecView
        recView.layoutManager = LinearLayoutManager(requireContext())
        recView.adapter = adapter

        val fabAdd: FloatingActionButton = view.findViewById(R.id.fabAdd)
//        fabAdd.setOnClickListener {
//            View.OnClickListener {
//                findNavController().navigate(R.id.action_list_to_add)
//            }
//            val fab = viewbinding.fab
        fabAdd.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_new_to_add)
        })

        adapter.submitList(dataRepo.getData())
        // W obu fragmentach
//        val sharedViewModel: SharedViewModel by viewModels()


//        sharedViewModel.data.observe(viewLifecycleOwner, Observer {
//            // Aktualizuj dane w adapterze
//            recView.adapter?.notifyDataSetChanged()
//            adapter?.updateData(it)
////            if (adapter != null) {
////                adapter.notifyDataSetChanged()
////            }
//        })
        recView.postDelayed({
            adapter.submitList(dataRepo.getData())
        }, 100)
    }



    inner class MyAdapter(private val onItemAction: (item:DBItem, action:Int)->Unit) : ListAdapter<DBItem, MyAdapter.MyViewHolder>(DiffCallback)
         {
//        fun updateData(newData: List<DataItem>) {
//            data.clear()
//            data.addAll(newData)
//            notifyDataSetChanged()
//        }

        inner class MyViewHolder(viewBinding : ListRowBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {
            val tv1: TextView = viewBinding.lrowTv1
            val tv2: TextView = viewBinding.lrowTv2
            val img: ImageView = viewBinding.lrowImage
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val viewBinding = ListRowBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
            val holder = MyViewHolder(viewBinding)

            //tu listenery

            return MyViewHolder(viewBinding)

        }

        override fun getItemCount(): Int {
            return dataRepo.getData()?.size ?: 20
        }
        @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
        override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

            val item = getItem(position)
            if(item.text_sport=="") item.text_sport="Tenis"

            holder.tv1.text = item.text_sport

            holder.itemView.setOnClickListener {
//                Toast.makeText(requireContext(),
//                    "You clicked: " + (position + 1),
//                    Toast.LENGTH_SHORT).show()
//                val position = holder.adapterPosition
//                // val item = getItem(position)
//                if (position != RecyclerView.NO_POSITION) {
//                    val item = getItem(position)
                    onItemAction(item, 1)
               // }
//                val data = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
//                data.edit().putLong("id", item.id.toLong()).apply()
//                findNavController().navigate(R.id.fragmentDetails)
//
//                val bundle = Bundle()
//                bundle.putInt("selectedItemPosition", position)
//                val fragmentDetails = FragmentDetails()
//                fragmentDetails.arguments = bundle
//                findNavController().navigate(R.id.action_list_to_details, bundle)

            }
            holder.itemView.setOnLongClickListener {
             //   val position = holder.adapterPosition
                Log.d("index", position.toString())
             //   val item = getItem(position)
                val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setTitle("Potwierdzenie")
                alertDialogBuilder.setMessage("Czy na pewno chcesz usunąć "+item.text_main+" ?")

                alertDialogBuilder.setPositiveButton("Tak") { dialog, which ->
                    // Usunięcie elementu
                    onItemAction(item,2)
//                    if (dataRepo.deleteItem(item)) {
//                        data = dataRepo.getData()!!
//                        notifyDataSetChanged()
//                    }
//                    dialog.dismiss()
                }

                alertDialogBuilder.setNegativeButton("Nie") { dialog, which ->
                    // Anulowanie usunięcia
                    dialog.dismiss()
                }

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()

                true
            }


//            val updatedItem = DBItem().apply {
//                id = data[position].id
//                text_main = data[position].text_main
            when (item.item_type) {
                0 -> {
                    holder.img.setImageResource(R.drawable.ic_img1)
//                        data[position].text_sport = "Gimnastyka"
//                        if (data[position].item_opis == "Default opis") {
//                            data[position].item_opis =
//                                " Pamiętajmy, że dzięki gimnastyce rozwijamy zarówno układ ruchowy, " +
//                                        "jak i nerwowy, a więc siłę, koordynację, gibkość, szybkość i wytrzymałość naszego organizmu."
//                        }
                }
                1 -> {
                    holder.img.setImageResource(R.drawable.ic_img2)
//                        data[position].text_sport = "Piłka ręczna"
//                        if (data[position].item_opis == "Default opis") {
//                            data[position].item_opis =
//                                " Piłka ręczna jest sportem drużynowym opartym na zasadach \"fair play\". "
//                        }
                }
                2 -> {
                    holder.img.setImageResource(R.drawable.ic_img3)
//                        data[position].text_sport = "Koszykówka"
//                        if (data[position].item_opis == "Default opis") {
//                            data[position].item_opis =
//                                " Koszykówka (lub piłka koszykowa) – dyscyplina sportu drużynowego (sport olimpijski)," +
//                                        " w której dwie pięcioosobowe drużyny grają przeciwko sobie."
//                        }
                }
                3 -> {
                    holder.img.setImageResource(R.drawable.ic_img4)
//                        data[position].text_sport = "Tenis"
//                        if (data[position].item_opis == "Default opis") {
//                            data[position].item_opis =
//                                " Mecz tenisowy składa się z setów, sety z gemów, a gemy z punktów."
//                        }
                }

                else -> {
//                        text_sport = data[position].text_sport
//                        item_opis = "Inny opis"
                    true

                }
            }

//                text_sport = data[position].text_sport
//                item_beginner = data[position].item_beginner
//                item_trudnosc = data[position].item_trudnosc
//                item_type = data[position].item_type
//            }

//            dataRepo.updateItem(updatedItem)

            holder.tv2.text = item.text_sport + " poziom: "+item.item_trudnosc
        }


    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DBItem>(){
            override fun areItemsTheSame(oldItem: DBItem, newItem: DBItem): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: DBItem, newItem: DBItem): Boolean {
                return oldItem == newItem
            }

        }
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}