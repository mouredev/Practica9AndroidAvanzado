package com.braismoure.marvelheroes.data.repository.datasource

import com.braismoure.marvelheroes.data.db.MarvelHeroDatabase
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by MoureDev by Brais Moure on 1/7/18.
 * www.mouredev.com
 */
class LocalMarvelHeroesDataSource(val marvelHeroDatabase: MarvelHeroDatabase)
    : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            marvelHeroDatabase
                    .getMarvelHeroDao()
                    .getAllHeroes()
                    .toFlowable()

    fun saveHeroes(heroes: List<MarvelHeroEntity>) {
        Observable.fromCallable {
            marvelHeroDatabase.getMarvelHeroDao().removeAndInsertHeroes(heroes)
        }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun updateHero(hero: MarvelHeroEntity) {
        Observable.fromCallable {
            marvelHeroDatabase.getMarvelHeroDao().updateHero(hero)
        }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

}