package com.braismoure.marvelheroes.data.model

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcelable
import com.braismoure.marvelheroes.data.db.Converters
import kotlinx.android.parcel.Parcelize

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
@SuppressLint("ParcelCreator")
@Entity(tableName = "heroes")
@Parcelize
data class MarvelHeroEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val name: String,
        @ColumnInfo(name = "photo_url")
        val photoUrl: String,
        @ColumnInfo(name = "real_name")
        val realName: String,
        val height: String,
        val power: String,
        val abilities: String,
        @TypeConverters(Converters::class)
        val groups: Array<String>,
        var favorite: Boolean,
        var rate: Int,
        var comment: String
) : Parcelable