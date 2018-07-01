package com.braismoure.marvelheroes.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.braismoure.marvelheroes.data.db.MarvelHeroDatabase
import com.braismoure.marvelheroes.data.mapper.MarvelHeroEntityMapper
import com.braismoure.marvelheroes.data.net.MarvelHeroesService
import com.braismoure.marvelheroes.data.repository.MarvelHeroesRepository
import com.braismoure.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.braismoure.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMarvelHeroMapper(): MarvelHeroEntityMapper = MarvelHeroEntityMapper()

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MarvelHeroDatabase =
            Room.databaseBuilder(context, MarvelHeroDatabase::class.java, "heroes.db").build()

    @Singleton
    @Provides
    fun provideLocalMarvelHeroesDataSource(marvelHeroDatabase: MarvelHeroDatabase): LocalMarvelHeroesDataSource =
            LocalMarvelHeroesDataSource(marvelHeroDatabase)

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSource(marvelHeroesService: MarvelHeroesService,
                                            marvelHeroEntityMapper: MarvelHeroEntityMapper)
            : RemoteMarvelHeroesDataSource =
            RemoteMarvelHeroesDataSource(marvelHeroesService, marvelHeroEntityMapper)

    @Provides
    @Singleton
    fun provideMarvelHeroesRepository(
            marvelRemoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
            localMarvelHeroesDataSource: LocalMarvelHeroesDataSource): MarvelHeroesRepository =
            MarvelHeroesRepository(marvelRemoteMarvelHeroesDataSource, localMarvelHeroesDataSource)

}