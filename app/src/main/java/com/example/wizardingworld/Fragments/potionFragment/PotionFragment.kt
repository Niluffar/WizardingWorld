package com.example.wizardingworld.Fragments.potionFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.potionsData.Potion

private const val POTION = "potion"
class PotionFragment : Fragment() {
    private lateinit var potion: Potion
    private lateinit var nameTextView: TextView
    private lateinit var characteristicsTextView: TextView
    private lateinit var effectTextView: TextView
    private lateinit var ingredientsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        potion = arguments?.getSerializable(POTION) as Potion
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_potion, container, false)
        nameTextView= view.findViewById(R.id.name)
        characteristicsTextView = view.findViewById(R.id.characteristics)
        effectTextView = view.findViewById(R.id.effect)
        ingredientsTextView = view.findViewById(R.id.ingredients)

        nameTextView.text = potion.attributes.name
        characteristicsTextView.text = potion.attributes.characteristics
        effectTextView.text = potion.attributes.effect
        ingredientsTextView.text = potion.attributes.ingredients

        return view
    }

    companion object {
        fun newInstance(potion: Potion): PotionFragment {
            val args = Bundle().apply {
                putSerializable(POTION, potion)
            }
            return PotionFragment().apply {
                arguments = args
            }
        }

    }
}