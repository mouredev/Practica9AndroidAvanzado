package com.braismoure.marvelheroes.data.repository

import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import com.braismoure.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.braismoure.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import io.reactivex.Flowable

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class MarvelHeroesRepository(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                             private val localMarvelHeroesDataSource: LocalMarvelHeroesDataSource) {

    fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
        getHeroesFromDb().concatWith(getHeroesFromRemote())

    private fun getHeroesFromDb(): Flowable<List<MarvelHeroEntity>> =
            localMarvelHeroesDataSource.getMarvelHeroesList()

    private fun getHeroesFromRemote(): Flowable<List<MarvelHeroEntity>> =
            remoteMarvelHeroesDataSource.getMarvelHeroesList()
                    .doOnNext { localMarvelHeroesDataSource.saveHeroes(it) }

    fun updateMarvelHero(hero: MarvelHeroEntity) {
        localMarvelHeroesDataSource.updateHero(hero)
    }


}