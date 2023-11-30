package com.example.wizardingworld.Fragments

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.wizardingworld.R

/**
 * A simple [Fragment] subclass.
 * Use the [MainPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MainPageFragment : Fragment(), AdapterView.OnItemSelectedListener {

    interface Callbacks{
        fun onCategorySelected(itemSelectedId: Long)
    }



    private var callbacks: Callbacks? = null
    private val packageName = "com.example.wizardingworld"
    private var itemSelectedId: Long = 0
    private lateinit var searchBtn: Button

//    private lateinit var characters: Button



    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_page, container, false)

        searchBtn = view.findViewById(R.id.search_btn)
        val videoView = view.findViewById<VideoView>(R.id.myVideo)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.background)
        videoView.setVideoURI(uri)
        videoView.start()

        videoView.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
        })

//    spinner
        val spinner = view.findViewById<Spinner>(R.id.spinner1)

        getActivity()?.let {
            ArrayAdapter.createFromResource(
                it.getBaseContext(),
                R.array.wizard_elements,
                R.layout.my_selected_item
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.my_dropdown_item)
                spinner.adapter = adapter
            }
        }

        spinner.onItemSelectedListener = this

        //      search Button listener
        searchBtn.setOnClickListener { view: View ->

            callbacks?.onCategorySelected(itemSelectedId)

        }

        return view;
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        itemSelectedId = id
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }


}