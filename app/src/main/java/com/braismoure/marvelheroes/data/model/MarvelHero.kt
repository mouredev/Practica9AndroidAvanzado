package com.braismoure.marvelheroes.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
data class MarvelHero(
        val name: String = "",
        @SerializedName("photo")
        val photoUrl: String = "",
        val realName: String = "",
        val height: String = "",
        val power: String = "",
        val abilities: String = "",
        val groups: String = ""
)