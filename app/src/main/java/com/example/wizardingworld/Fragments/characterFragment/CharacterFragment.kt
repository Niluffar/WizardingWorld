package com.example.wizardingworld.Fragments.characterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.charactersData.Character

private const val CHARACTER = "character"
class CharacterFragment : Fragment() {
    private lateinit var name: TextView
    private lateinit var ancestry: TextView
    private lateinit var gender: TextView
    private lateinit var hairColor: TextView
    private lateinit var image: ImageView
    private lateinit var house: TextView
    private lateinit var patronus: TextView
    private lateinit var wizard: TextView
    private lateinit var alive: TextView
    private lateinit var actor: TextView
    private lateinit var dateOfBirth: TextView
    private lateinit var died: TextView
    private lateinit var eyeColor: TextView
    private lateinit var character: Character
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        character = arguments?.getSerializable(CHARACTER) as Character
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_character, container, false)
        name = view.findViewById(R.id.name)
        ancestry = view.findViewById(R.id.ancestry)
        gender = view.findViewById(R.id.gender)
        hairColor = view.findViewById(R.id.hairColor)
        patronus = view.findViewById(R.id.patronus)
        wizard = view.findViewById(R.id.wizard)
        alive = view.findViewById(R.id.alive)
        actor = view.findViewById(R.id.actor)
        dateOfBirth = view.findViewById(R.id.dateOfBirth)
        died = view.findViewById(R.id.died)
        eyeColor = view.findViewById(R.id.eyeColor)
        image = view.findViewById(R.id.image)

        name.text = character.name
        ancestry.text = character.ancestry
        gender.text = character.gender
        hairColor.text = character.hairColour
        patronus.text = character.patronus
        wizard.text = character.wizard.toString()
        alive.text = character.alive.toString()
        actor.text = character.actor
        dateOfBirth.text = character.dateOfBirth
        died.text = character.died
        eyeColor.text = character.eyeColour

        if (character.image != null) {
            Glide.with(requireContext()).load(character.image!!).into(image)
        }else {
            image.setImageDrawable(requireContext().getDrawable(R.mipmap.character_placeholder))
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance(character: Character): CharacterFragment {
            val args = Bundle().apply {
                putSerializable(CHARACTER, character)
            }
            return CharacterFragment().apply {
                arguments = args
            }
        }
    }
}