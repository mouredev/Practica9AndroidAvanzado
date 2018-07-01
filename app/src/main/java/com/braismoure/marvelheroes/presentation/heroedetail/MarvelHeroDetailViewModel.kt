package com.braismoure.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.MutableLiveData
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import com.braismoure.marvelheroes.data.repository.MarvelHeroesRepository
import com.braismoure.marvelheroes.util.mvvm.BaseViewModel
import javax.inject.Inject

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class MarvelHeroDetailViewModel @Inject constructor(var marvelHeroesRepository: MarvelHeroesRepository) : BaseViewModel() {

    val marvelHero: MutableLiveData<MarvelHeroEntity> = MutableLiveData()

    fun updateHero(hero: MarvelHeroEntity) {
        marvelHeroesRepository.updateMarvelHero(hero)
    }

}