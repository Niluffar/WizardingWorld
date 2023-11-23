package com.example.wizardingworld.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.SpellsViewModel
import com.example.wizardingworld.sampledata.booksData.Book
import com.example.wizardingworld.sampledata.charactersData.Character
import com.example.wizardingworld.sampledata.spellsData.Spell

class SpellsListFragment : Fragment() {
    private lateinit var spellsViewModel: SpellsViewModel
    private  lateinit var spellsRecyclerView: RecyclerView
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           spellsViewModel = ViewModelProvider(this).get(SpellsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_view, container, false)
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
    private inner class SpellHolder(view: View): RecyclerView.ViewHolder(view){
        private lateinit var spell: Spell
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val categoryTextView: TextView = itemView.findViewById(R.id.category)
        val lightTextView: TextView = itemView.findViewById(R.id.light)
        val effectTextView: TextView = itemView.findViewById(R.id.effect)

        fun bind(spell: Spell){
            this.spell = spell
            nameTextView.text = spell.attributes.name
            categoryTextView.text = spell.attributes.category
            lightTextView.text = spell.attributes.light
            effectTextView.text = spell.attributes.effect

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