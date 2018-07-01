package com.braismoure.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.braismoure.marvelheroes.R
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import com.braismoure.marvelheroes.presentation.MainApp
import com.braismoure.marvelheroes.util.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class HeroesListActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var heroesListViewModel: HeroesListViewModel

    private val adapter = HeroesListAdapter(
            { hero : MarvelHeroEntity, image : ImageView -> goToHeroDetail(hero, image) },
            { hero : MarvelHeroEntity -> tapFavorite(hero) })

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecycler()
        setUpViewModel()
    }

    override fun onResume() {
        super.onResume()
        heroesListViewModel.getMarvelHeroesList()
    }

    private fun inject() {
        (application as MainApp).component.inject(this)
    }

    private fun setUpRecycler() {
        heroesListRecycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        heroesListRecycler.itemAnimator = DefaultItemAnimator()
        heroesListRecycler.adapter = adapter
    }

    private fun setUpViewModel() {
        heroesListViewModel = ViewModelProviders.of(this, viewModelFactory).get(HeroesListViewModel::class.java)
        bindEvents()
    }

    private fun bindEvents() {
        heroesListViewModel.isLoadingState.observe(this, Observer { isLoading ->
            isLoading?.let {
                showLoading(it)
            }
        })

        heroesListViewModel.marvelHeroesListState.observe(this, Observer { userList ->
            userList?.let {
                showHeroesList(it)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        heroesListLoading.visibility = if(isLoading) View.VISIBLE else View.GONE
        heroesListRecycler.visibility = if(isLoading) View.INVISIBLE else View.VISIBLE
    }

    private fun showHeroesList(heroes: List<MarvelHeroEntity>) {
        adapter.swapData(heroes)
    }

    private fun goToHeroDetail(hero: MarvelHeroEntity, image: View) {
        navigator.goToHeroDetail(this, hero, image)
    }

    private fun tapFavorite(hero: MarvelHeroEntity) {
        heroesListViewModel.updateHero(hero)
    }

}
