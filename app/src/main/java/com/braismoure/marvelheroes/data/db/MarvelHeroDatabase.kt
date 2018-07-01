package com.braismoure.marvelheroes.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity

/**
 * Created by MoureDev by Brais Moure on 1/7/18.
 * www.mouredev.com
 */
@Database(entities = [MarvelHeroEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MarvelHeroDatabase : RoomDatabase() {

    abstract fun getMarvelHeroDao(): MarvelHeroDao

}