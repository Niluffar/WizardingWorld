package com.example.wizardingworld.Fragments.characterFragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
import com.example.wizardingworld.sampledata.CharacterViewModel
import com.example.wizardingworld.sampledata.booksData.Book
import com.example.wizardingworld.sampledata.charactersData.Character
 val TAG = "CharactersListFragment"
class CharactersListFragment : Fragment() {

    interface Callbacks {
        fun onCharacterSelected(character: Character)
        fun onBackBtnSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var charactersRecyclerView: RecyclerView
    private lateinit var characters: List<Character>


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


                        val searchText = searchEditTextView.text.toString().lowercase()
                        val newCharacters = characters.filter {

//                                Log.d(TAG, it.name.lowercase().slice(0..<searchText.length-1))
//                            Log.d(TAG, it.attributes.title.lowercase().slice(start..searchText.length-1))
                            it.name.lowercase().slice(0..searchText.length-1) == searchText
                        }

                        charactersRecyclerView.adapter = CharacterAdapter(newCharacters)

            }
        })
        // Inflate the layout for this fragment
        charactersRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        charactersRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterViewModel.characterLiveData.observe(
            viewLifecycleOwner,
            Observer { charactersList: List<Character> ->
                characters = charactersList
                charactersRecyclerView.adapter = CharacterAdapter(charactersList)
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

            name.text = character.name
            house.text = character.house

            if (character.image.length > 0) {
                Glide.with(context!!).load(character.image!!).into(image)
            }else {
                image.setImageDrawable(context!!.getDrawable(R.mipmap.character_placeholder))
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