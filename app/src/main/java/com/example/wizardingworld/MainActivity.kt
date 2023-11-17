package com.example.wizardingworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import android.media.MediaPlayer
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView = findViewById<VideoView>(R.id.myVideo)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.background)
        videoView.setVideoURI(uri)
        videoView.start()

        videoView.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
        })

        val spinner = findViewById<Spinner>(R.id.spinner1)

        ArrayAdapter.createFromResource(
            this,
            R.array.wizard_elements,
            R.layout.my_selected_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.my_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }
}