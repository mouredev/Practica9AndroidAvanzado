package com.braismoure.marvelheroes.data.net

import com.braismoure.marvelheroes.data.model.MarvelHeroesResponse
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
interface MarvelHeroesService {

    @GET(".")
    fun getMarvelHeroesList(): Flowable<MarvelHeroesResponse>

}