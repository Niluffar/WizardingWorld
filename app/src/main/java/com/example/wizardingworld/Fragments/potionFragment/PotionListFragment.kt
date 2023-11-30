package com.example.wizardingworld.Fragments.potionFragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.PotionsViewModel
import com.example.wizardingworld.sampledata.moviesData.Movie
import com.example.wizardingworld.sampledata.potionsData.Potion


class PotionListFragment : Fragment() {

    interface Callbacks{
        fun onPotionSelected(potion: Potion)
        fun onBackBtnSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var potionsViewModel: PotionsViewModel
    private lateinit var potionsRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        potionsViewModel = ViewModelProvider(this).get(PotionsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.recycler_view, container, false)
        val searchEditTextView: EditText = view.findViewById(R.id.search_view)
        val backBtn: Button = view.findViewById(R.id.back_btn)
        backBtn.setOnClickListener{
            callbacks?.onBackBtnSelected()
        }

        searchEditTextView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                Log.d(TAG, "afterTextChanged")

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
//                Log.d(TAG, "beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                potionsViewModel.potionLiveData.observe(
                    viewLifecycleOwner,
                    Observer { potions: List<Potion> ->

                        val searchText = searchEditTextView.text.toString().lowercase()


                        val newPotions = potions.filter {
//                            Log.d(TAG, it.attributes.title.lowercase().slice(0..searchText.length-1))
//                            Log.d(TAG, it.attributes.title.lowercase().slice(start..searchText.length-1))
                            it.attributes.name.lowercase().slice(0..searchText.length-1) == searchText
                        }

                        potionsRecyclerView.adapter = PotionAdapter(newPotions)
                    })
            }
        })
        potionsRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        potionsRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        potionsViewModel.potionLiveData.observe(
            viewLifecycleOwner,
            Observer { potions: List<Potion> ->
                potionsRecyclerView.adapter = PotionAdapter(potions)
            })
    }

    private inner class PotionHolder(view: View): RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var potion: Potion
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        init{
            itemView.setOnClickListener(this)
        }

        fun bind(potion: Potion){
            this.potion = potion
            nameTextView.text = potion.attributes.name


        }
        override fun onClick(v: View?) {
//            Toast.makeText(context, " ${movie.attributes.title} passed!", Toast.LENGTH_SHORT).show()
            callbacks?.onPotionSelected(potion)
        }
    }
    private inner class PotionAdapter(private val potions: List<Potion>):
        RecyclerView.Adapter<PotionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PotionHolder {
            val view = layoutInflater.inflate(R.layout.list_item_potion, parent, false)
            return PotionHolder(view)
        }


        override fun getItemCount() = potions.size

        override fun onBindViewHolder(holder: PotionHolder, position: Int) {
            val potion = potions[position]
            holder.bind(potion)
        }

    }




}