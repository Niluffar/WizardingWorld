package com.example.wizardingworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wizardingworld.Fragments.BookFragment
import com.example.wizardingworld.Fragments.BookListFragment
import com.example.wizardingworld.Fragments.CharacterFragment
import com.example.wizardingworld.Fragments.CharactersListFragment
import com.example.wizardingworld.Fragments.MainPageFragment
import com.example.wizardingworld.Fragments.SpellsListFragment
import com.example.wizardingworld.sampledata.booksData.Book
import com.example.wizardingworld.sampledata.charactersData.Character


class MainActivity : AppCompatActivity(), MainPageFragment.Callbacks, BookListFragment.Callbacks, CharactersListFragment.Callbacks {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = MainPageFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }



    }
    override fun onCategorySelected(itemId: Long) {

        var fragment = CharactersListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()


//
//        if(itemId==0.toLong())
//            fragment = BookListFragment()
//        else if(itemId==1.toLong())
//            fragment = CharactersListFragment()

//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()

    }

    override fun onBookSelected(clickedBook: Book){
        val fragment = BookFragment.newInstance(clickedBook)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()


    }

    override fun onCharacterSelected(character: Character) {
        val fragment = CharacterFragment.newInstance(character)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }


}