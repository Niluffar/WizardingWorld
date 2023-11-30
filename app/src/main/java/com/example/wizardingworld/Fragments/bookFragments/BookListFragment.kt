package com.example.wizardingworld.Fragments.bookFragments

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
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.BooksViewModel
import com.example.wizardingworld.sampledata.booksData.Book


class BookListFragment : Fragment() {
    val TAG = "BookListFragment"
    interface Callbacks {
        fun onBookSelected(book: Book)
        fun onBackBtnSelected()

    }
    private var callbacks: Callbacks? = null
    private lateinit var booksViewModel: BooksViewModel
    private lateinit var booksRecyclerView: RecyclerView
//    private var adapter: BookAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        booksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.recycler_view, container, false)
        val searchEditTextView: EditText = view.findViewById(R.id.search_view)
        val backBtn: Button = view.findViewById(R.id.back_btn)
        backBtn.setOnClickListener{
            callbacks?.onBackBtnSelected()
        }


        searchEditTextView.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
//                Log.d(TAG, "afterTextChanged")

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
//                Log.d(TAG, "beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                booksViewModel.bookLiveData.observe(
                    viewLifecycleOwner,
                    Observer { books: List<Book> ->

                        val searchText = searchEditTextView.text.toString().lowercase()


                        val newBooks = books.filter {
//                            Log.d(TAG, it.attributes.title.lowercase().slice(0..searchText.length-1))
//                            Log.d(TAG, it.attributes.title.lowercase().slice(start..searchText.length-1))
                            it.attributes.title.lowercase().slice(0..searchText.length-1) == searchText
                        }

                        booksRecyclerView.adapter = BookAdapter(newBooks)
                    })
            }
        })


        booksRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        booksRecyclerView.layoutManager= LinearLayoutManager(context)

        return view
    }

    companion object {
        fun newInstance() = BookListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        booksViewModel.bookLiveData.observe(
            viewLifecycleOwner,
            Observer { books: List<Book> ->
                booksRecyclerView.adapter = BookAdapter(books)
            })
    }

    private inner class BookHolder(view: View): RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var book: Book
        val titleTextView: TextView = itemView.findViewById(R.id.book_title)
        val dateTextView: TextView = itemView.findViewById(R.id.book_release_date)
        val authorTextView: TextView = itemView.findViewById(R.id.book_author)
        val pageNumTextView: TextView = itemView.findViewById(R.id.book_page_num)
        val bookCover: ImageView = itemView.findViewById(R.id.book_cover)


        init{
            itemView.setOnClickListener(this)
        }

        fun bind(book: Book){
            this.book = book
            titleTextView.text = book.attributes.title
            dateTextView.text = book.attributes.releaseDate
            authorTextView.text = book.attributes.author
            pageNumTextView.text = book.attributes.pages
            if (book.attributes.coverLink != null) {
                Glide.with(context!!).load(book.attributes.coverLink!!).into(bookCover)
            }
        }
        override fun onClick(v: View?) {
            Toast.makeText(context, " ${book.attributes.title} passed!", Toast.LENGTH_SHORT).show()
            callbacks?.onBookSelected(book)
        }
    }

    private inner class BookAdapter(private val books: List<Book>):
        RecyclerView.Adapter<BookHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
            val view = layoutInflater.inflate(R.layout.list_item_book, parent, false)
            return BookHolder(view)
        }


        override fun getItemCount() = books.size

        override fun onBindViewHolder(holder: BookHolder, position: Int) {
            val book = books[position]
            holder.bind(book)
        }

    }
}