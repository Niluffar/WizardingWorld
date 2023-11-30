package com.example.wizardingworld.Fragments.spellFragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.SpellsViewModel
import com.example.wizardingworld.sampledata.spellsData.Spell

class SpellsListFragment : Fragment() {
    interface Callbacks{
        fun onSpellSelected(spell: Spell)
        fun onBackBtnSelected()
    }
    private var callbacks: Callbacks? = null
    private lateinit var spellsViewModel: SpellsViewModel
    private  lateinit var spellsRecyclerView: RecyclerView
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           spellsViewModel = ViewModelProvider(this).get(SpellsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                spellsViewModel.spellsLiveData.observe(
                    viewLifecycleOwner,
                    Observer { spells: List<Spell> ->

                        val searchText = searchEditTextView.text.toString().lowercase()


                        val newSpells = spells.filter {
//                            Log.d(TAG, it.attributes.title.lowercase().slice(0..searchText.length-1))
//                            Log.d(TAG, it.attributes.title.lowercase().slice(start..searchText.length-1))
                            it.attributes.name.lowercase().slice(0..searchText.length-1) == searchText
                        }

                        spellsRecyclerView.adapter = SpellAdapter(newSpells)
                    })
            }
        })
        spellsRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        spellsRecyclerView.layoutManager = LinearLayoutManager(context)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spellsViewModel.spellsLiveData.observe(
            viewLifecycleOwner, Observer { spells: List<Spell> ->
                spellsRecyclerView.adapter = SpellAdapter(spells)
            }
        )
    }
    private inner class SpellHolder(view: View): RecyclerView.ViewHolder(view),View.OnClickListener{
        private lateinit var spell: Spell
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(spell: Spell){
            this.spell = spell
            nameTextView.text = spell.attributes.name
        }

        override fun onClick(v: View?) {
//            Toast.makeText(context, " ${movie.attributes.title} passed!", Toast.LENGTH_SHORT).show()
            callbacks?.onSpellSelected(spell)
        }

    }
    private inner class SpellAdapter(private val spells: List<Spell>):
        RecyclerView.Adapter<SpellHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellHolder {
            val view = layoutInflater.inflate(R.layout.list_item_spell, parent, false)
            return SpellHolder(view)
        }


        override fun getItemCount() = spells.size

        override fun onBindViewHolder(holder: SpellHolder, position: Int) {
            val spell = spells[position]
            holder.bind(spell)
        }
    }

}