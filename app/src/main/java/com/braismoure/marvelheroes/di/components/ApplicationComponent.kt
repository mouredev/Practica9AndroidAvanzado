package com.braismoure.marvelheroes.di.components

import com.braismoure.marvelheroes.di.modules.ApplicationModule
import com.braismoure.marvelheroes.di.modules.DataModule
import com.braismoure.marvelheroes.di.modules.NetModule
import com.braismoure.marvelheroes.presentation.heroedetail.MarvelHeroDetailActivity
import com.braismoure.marvelheroes.presentation.heroeslist.HeroesListActivity
import com.braismoure.marvelheroes.util.mvvm.ViewModelModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetModule::class,
    DataModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(heroesListActivity: HeroesListActivity)

    fun inject(marvelHeroDetailActivity: MarvelHeroDetailActivity)

}