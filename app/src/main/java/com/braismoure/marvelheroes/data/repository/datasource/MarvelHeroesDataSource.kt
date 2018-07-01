package com.braismoure.marvelheroes.data.repository.datasource

import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import io.reactivex.Flowable

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
interface MarvelHeroesDataSource {

    fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>>

}