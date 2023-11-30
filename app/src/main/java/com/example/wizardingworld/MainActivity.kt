package com.example.wizardingworld

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.wizardingworld.Fragments.bookFragments.BookFragment
import com.example.wizardingworld.Fragments.bookFragments.BookListFragment
import com.example.wizardingworld.Fragments.characterFragment.CharacterFragment
import com.example.wizardingworld.Fragments.characterFragment.CharactersListFragment
import com.example.wizardingworld.Fragments.MainPageFragment
import com.example.wizardingworld.Fragments.spellFragments.SpellsListFragment
import com.example.wizardingworld.Fragments.movieFragments.MovieFragment
import com.example.wizardingworld.Fragments.movieFragments.MovieListFragment
import com.example.wizardingworld.Fragments.potionFragment.PotionFragment
import com.example.wizardingworld.Fragments.potionFragment.PotionListFragment
import com.example.wizardingworld.Fragments.spellFragments.SpellFragment
import com.example.wizardingworld.sampledata.booksData.Book
import com.example.wizardingworld.sampledata.charactersData.Character
import com.example.wizardingworld.sampledata.moviesData.Movie
import com.example.wizardingworld.sampledata.potionsData.Potion
import com.example.wizardingworld.sampledata.spellsData.Spell


class MainActivity : AppCompatActivity(),
    MainPageFragment.Callbacks,
    BookListFragment.Callbacks,
    CharactersListFragment.Callbacks,
    MovieListFragment.Callbacks,
    PotionListFragment.Callbacks,
    SpellsListFragment.Callbacks{


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
    override fun onCategorySelected(itemSelectedId: Long) {
        var fragment: Fragment? = null


            if (itemSelectedId == 0.toLong())
                fragment = BookListFragment()
            else if (itemSelectedId == 1.toLong())
                fragment = CharactersListFragment()
            else if (itemSelectedId == 2.toLong())
                fragment = MovieListFragment()
            else if (itemSelectedId == 3.toLong())
                fragment = PotionListFragment()
            else if (itemSelectedId == 4.toLong())
                fragment = SpellsListFragment()


        if (fragment != null){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment!!)
            .commit()
        }
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

    override fun onMovieSelected(movie: Movie) {
        val fragment = MovieFragment.newInstance(movie)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onPotionSelected(potion: Potion) {
        val fragment = PotionFragment.newInstance(potion)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSpellSelected(spell: Spell) {
        val fragment = SpellFragment.newInstance(spell)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackBtnSelected() {

        val fragment = MainPageFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }


}