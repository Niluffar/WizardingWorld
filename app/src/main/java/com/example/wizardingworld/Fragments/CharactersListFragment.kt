package com.example.wizardingworld.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.CharacterViewModel
import com.example.wizardingworld.sampledata.charactersData.Character
private const val TAG = "CharactersListFragment"
class CharactersListFragment : Fragment() {

    interface Callbacks {
        fun onCharacterSelected(character: Character)
    }

    private var callbacks: Callbacks? = null
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var charactersRecyclerView: RecyclerView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_view, container, false)
        charactersRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        charactersRecyclerView.layoutManager = LinearLayoutManager(context)
        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterViewModel.characterLiveData.observe(
            viewLifecycleOwner,
            Observer { characters: List<Character> ->
                charactersRecyclerView.adapter = CharacterAdapter(characters)
            }
        )
    }
    private inner class CharacterHolder(view: View): RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var character: Character
        val name: TextView = itemView.findViewById(R.id.name)
        val house: TextView = itemView.findViewById(R.id.house)
        val image: ImageView = itemView.findViewById(R.id.image)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(character: Character){
            this.character = character
            Log.d(TAG, character.name)
            name.text = character.name
            house.text = character.house

            if (character.image != null) {
                Glide.with(context!!).load(character.image!!).into(image)
            }else {
                image.setImageDrawable(context!!.getDrawable(R.drawable.book_cover_placeholder))
            }
        }
        override fun onClick(v: View?) {
            Toast.makeText(context, " ${character.name} passed!", Toast.LENGTH_SHORT).show()
            callbacks?.onCharacterSelected(character)
        }
    }

    private inner class CharacterAdapter(private val characters: List<Character>):
        RecyclerView.Adapter<CharacterHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
            val view = layoutInflater.inflate(R.layout.list_item_character, parent, false)
            return CharacterHolder(view)
        }


        override fun getItemCount() = characters.size

        override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
            val character = characters[position]
            holder.bind(character)
        }

    }
}