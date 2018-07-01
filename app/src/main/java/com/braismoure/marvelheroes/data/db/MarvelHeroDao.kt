package com.braismoure.marvelheroes.data.db

import android.arch.persistence.room.*
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import io.reactivex.Maybe

/**
 * Created by MoureDev by Brais Moure on 1/7/18.
 * www.mouredev.com
 */
@Dao
abstract class MarvelHeroDao {

    @Query("SELECT * FROM heroes")
    abstract fun getAllHeroes(): Maybe<List<MarvelHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(heroes: List<MarvelHeroEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(hero: MarvelHeroEntity)

    @Query("DELETE FROM heroes")
    abstract fun deleteAllHeroes()

    @Query("DELETE FROM heroes WHERE real_name=:name")
    abstract fun deleteHero(name: String)

    @Transaction
    open fun removeAndInsertHeroes(heroes: List<MarvelHeroEntity>) {
        deleteAllHeroes()
        insertAll(heroes)
    }

    @Transaction
    open fun updateHero(hero: MarvelHeroEntity) {
        deleteHero(hero.realName)
        insert(hero)
    }

}