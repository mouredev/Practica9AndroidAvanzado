package com.braismoure.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import com.braismoure.marvelheroes.data.repository.MarvelHeroesRepository
import com.braismoure.marvelheroes.util.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class HeroesListViewModel @Inject constructor(var marvelHeroesRepository: MarvelHeroesRepository) : BaseViewModel() {

    val marvelHeroesListState: MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoadingState: MutableLiveData<Boolean> = MutableLiveData()

    fun getMarvelHeroesList() {
        marvelHeroesRepository.getMarvelHeroesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoadingState.postValue(true) }
                .doOnTerminate { isLoadingState.postValue(false) }
                .subscribeBy(
                        onNext = {
                            if(marvelHeroesListState.value != null) {
                                for(hero in it) {
                                    marvelHeroesListState.value!!.filter { hero.realName == it.realName }.firstOrNull()?.let {
                                        if(it.id.toInt() != 0) {
                                            // Variables a nivel local. Nos quedamos con las de BBDD.
                                            hero.favorite = it.favorite
                                            hero.rate = it.rate
                                            hero.comment = it.comment
                                        }
                                    }
                                }
                            }
                            marvelHeroesListState.value = it
                        },
                        onError = {
                            Log.d("HeroesListViewModel", it.toString())
                        }
                )
                .addTo(compositeDisposable)
    }

    fun updateHero(hero: MarvelHeroEntity) {
        marvelHeroesRepository.updateMarvelHero(hero)
    }

}