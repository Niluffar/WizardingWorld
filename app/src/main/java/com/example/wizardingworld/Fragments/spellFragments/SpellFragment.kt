package com.example.wizardingworld.Fragments.spellFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.spellsData.Spell

private const val SPELL = "spell"
class SpellFragment : Fragment() {
  private lateinit var nameTextView: TextView
  private lateinit var effectTextView: TextView
  private lateinit var categoryTextView: TextView
  private lateinit var lightTextView: TextView
  private lateinit var spell: Spell


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spell = arguments?.getSerializable(SPELL) as Spell

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_spell, container, false)
        nameTextView = view.findViewById(R.id.name)
        effectTextView = view.findViewById(R.id.effect)
        categoryTextView = view.findViewById(R.id.category)
        lightTextView = view.findViewById(R.id.light)

        nameTextView.text = spell.attributes.name
        effectTextView.text = spell.attributes.effect
        categoryTextView.text = spell.attributes.category
        lightTextView.text = spell.attributes.light

        return view
    }

    companion object {
        fun newInstance(spell: Spell): SpellFragment {
            val args = Bundle().apply {
                putSerializable(SPELL, spell)
            }
            return SpellFragment().apply {
                arguments = args
            }
        }
    }
}