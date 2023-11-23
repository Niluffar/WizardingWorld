package com.example.wizardingworld.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wizardingworld.R
import com.example.wizardingworld.sampledata.booksData.Book

private const val TAG = "BookFragment"
private const val BOOK_CLICKED = "book"
class BookFragment : Fragment() {
    private lateinit var titleTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var pageNumTextView: TextView
    private lateinit var bookCover: ImageView
    private lateinit var bookDescription: TextView
    private lateinit var bookDedication: TextView
    private lateinit var book: Book


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        book = arguments?.getSerializable(BOOK_CLICKED) as Book

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book, container, false)
        titleTextView = view.findViewById(R.id.book_title)
        dateTextView = view.findViewById(R.id.book_release_date)
        authorTextView = view.findViewById(R.id.book_author)
        pageNumTextView = view.findViewById(R.id.book_page_num)
        bookDedication = view.findViewById(R.id.book_dedication)
        bookDescription = view.findViewById(R.id.book_contents)
        bookCover = view.findViewById(R.id.book_cover)

        titleTextView.text = book.attributes.title
        dateTextView.text = book.attributes.releaseDate
        authorTextView.text = book.attributes.author
        pageNumTextView.text = book.attributes.pages
        bookDedication.text = book.attributes.dedication
        bookDescription.text = book.attributes.summary

        if (book.attributes.coverLink != null) {
            Glide.with(requireContext()).load(book.attributes.coverLink!!).into(bookCover)
        }else {
            bookCover.setImageDrawable(requireContext().getDrawable(R.drawable.book_cover_placeholder))
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance(clickedBook : Book): BookFragment {
            val args = Bundle().apply {
                putSerializable(BOOK_CLICKED, clickedBook)
            }
            return BookFragment().apply {
                arguments = args
            }
        }

    }
}