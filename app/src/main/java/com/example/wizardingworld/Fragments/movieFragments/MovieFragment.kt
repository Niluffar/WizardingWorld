package com.example.wizardingworld.Fragments.movieFragments

//import android.R

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.moviesData.Movie
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


private const val MOVIE = "movie"
class MovieFragment : Fragment() {
    private lateinit var movie: Movie
    private lateinit var title: TextView
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var poster: ImageView
    private lateinit var releaseDate: TextView
    private lateinit var runningTime: TextView
    private lateinit var summary: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getSerializable(MOVIE) as Movie



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        title = view.findViewById(R.id.title)
        releaseDate = view.findViewById(R.id.release_date)
        runningTime = view.findViewById(R.id.running_time)
        summary = view.findViewById(R.id.summary)
        poster = view.findViewById(R.id.poster)
        youTubePlayerView= view.findViewById(R.id.youtube_player_view)

        title.text = movie.attributes.title
        releaseDate.text = movie.attributes.releaseDate
        runningTime.text = movie.attributes.runningTime
        summary.text = movie.attributes.summary


        // below two lines are used to set our screen orientation in landscape mode.
//        activity(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)



        // below line of code is to hide our action bar.
//        supportActionBar?.hide()

        // declaring variable for youtubePlayer view

        // below line is to place your youtube player in a full screen mode (i.e landscape mode)
//        youTubePlayerView.enterFullScreen()
//        youTubePlayerView.toggleFullScreen()

        // here we are adding observer to our youtubeplayerview.
        lifecycle.addObserver(youTubePlayerView)

        // below method will provides us the youtube player ui controller such
//        // as to play and pause a video to forward a video and many more features.
//        youTubePlayerView.getPlayerUiController()
//
//        // below line is to enter full screen mode.
//        youTubePlayerView.enterFullScreen()
//        youTubePlayerView.toggleFullScreen()

        // adding listener for our youtube player view.
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = movie.attributes.trailer.substring(movie.attributes.trailer.length-11, movie.attributes.trailer.length)
                youTubePlayer.loadVideo(videoId, 0f)
            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state)
            }
        })


    if (movie.attributes.poster != null) {
            Glide.with(requireContext()).load(movie.attributes.poster!!).into(poster)
        }else {
            poster.setImageDrawable(requireContext().getDrawable(R.mipmap.character_placeholder))
        }

        return view
    }

    companion object {

        fun newInstance(movie: Movie): MovieFragment {
            val args = Bundle().apply {
                putSerializable(MOVIE, movie)
            }
            return MovieFragment().apply {
                arguments = args
            }
        }
    }
}