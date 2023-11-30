package com.example.wizardingworld.Fragments.movieFragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.wizardingworld.Fragments.bookFragments.BookListFragment
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.MoviesViewModel
import com.example.wizardingworld.sampledata.booksData.Book
import com.example.wizardingworld.sampledata.moviesData.Movie


class MovieListFragment : Fragment() {

    interface Callbacks{
        fun onMovieSelected(movie: Movie)
        fun onBackBtnSelected()
    }

    private var callbacks: Callbacks?  = null
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesRecyclerView: RecyclerView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.recycler_view, container, false)
        val searchEditTextView: EditText = view.findViewById(R.id.search_view)
        val backBtn: Button = view.findViewById(R.id.back_btn)
        backBtn.setOnClickListener{
            callbacks?.onBackBtnSelected()
        }

        searchEditTextView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                moviesViewModel.movieLiveData.observe(
                    viewLifecycleOwner,
                    Observer { movies: List<Movie> ->

                        val searchText = searchEditTextView.text.toString().lowercase()


                        val newMovies = movies.filter {
                            it.attributes.title.lowercase().slice(0..searchText.length-1) == searchText
                        }

                        moviesRecyclerView.adapter = MovieAdapter(newMovies)
                    })
            }
        })
        moviesRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.movieLiveData.observe(
            viewLifecycleOwner,
            Observer { movies: List<Movie> ->
                moviesRecyclerView.adapter = MovieAdapter(movies)
            })
    }

    private inner class MovieHolder(view: View): RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var movie: Movie
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val dateTextView: TextView = itemView.findViewById(R.id.release_date)
        val runningTimeTextView: TextView = itemView.findViewById(R.id.running_time)

        val poster: ImageView = itemView.findViewById(R.id.poster)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie){
            this.movie = movie
            titleTextView.text = movie.attributes.title
            dateTextView.text = movie.attributes.releaseDate
            runningTimeTextView.text = movie.attributes.runningTime

            if (movie.attributes.poster != null) {
                Glide.with(context!!).load(movie.attributes.poster!!).into(poster)
            }else {
                poster.setImageDrawable(context!!.getDrawable(R.mipmap.character_placeholder))
            }
        }
        override fun onClick(v: View?) {
//            Toast.makeText(context, " ${movie.attributes.title} passed!", Toast.LENGTH_SHORT).show()
            callbacks?.onMovieSelected(movie)
        }
    }

    private inner class MovieAdapter(private val movies: List<Movie>):
        RecyclerView.Adapter<MovieHolder >() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
            val view = layoutInflater.inflate(R.layout.list_item_movie, parent, false)
            return MovieHolder(view)
        }


        override fun getItemCount() = movies.size

        override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            val movie = movies[position]
            holder.bind(movie)
        }

    }


}