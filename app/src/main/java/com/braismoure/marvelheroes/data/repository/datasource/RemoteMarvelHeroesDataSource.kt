package com.braismoure.marvelheroes.data.repository.datasource

import com.braismoure.marvelheroes.data.mapper.MarvelHeroEntityMapper
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import com.braismoure.marvelheroes.data.net.MarvelHeroesService
import io.reactivex.Flowable

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class RemoteMarvelHeroesDataSource(private val marvelHeroesService: MarvelHeroesService,
                                   private val marvelHeroEntityMapper: MarvelHeroEntityMapper):
        MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            marvelHeroesService.getMarvelHeroesList()
                    .map { it.superheroes }
                    .map { marvelHeroEntityMapper.transformList(it) }

}