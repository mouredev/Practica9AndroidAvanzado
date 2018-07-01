package com.braismoure.marvelheroes.data.mapper

import com.braismoure.marvelheroes.data.model.MarvelHero
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class MarvelHeroEntityMapper : Mapper<MarvelHero, MarvelHeroEntity> {

    override fun transform(input: MarvelHero): MarvelHeroEntity =
            MarvelHeroEntity(
                    0,
                    input.name,
                    input.photoUrl,
                    input.realName,
                    input.height,
                    input.power,
                    input.abilities,
                    getGroupsFromMarvelHero(input),
                    false,
                    -1,
                    "")

    override fun transformList(inputList: List<MarvelHero>): List<MarvelHeroEntity> =
            inputList.map { transform(it) }


    private fun getGroupsFromMarvelHero(marvelHero: MarvelHero): Array<String> =
            marvelHero.groups.replace("\\s".toRegex(), "").split(",").toTypedArray()

}