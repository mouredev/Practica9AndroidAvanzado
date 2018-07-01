package com.braismoure.marvelheroes.data.mapper

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
interface Mapper<in R, out T> {

    fun transform(input: R): T
    fun transformList(inputList: List<R>): List<T>

}