package com.braismoure.marvelheroes.presentation.heroeslist

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.braismoure.marvelheroes.R
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import kotlinx.android.synthetic.main.item_hero.view.*

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
typealias OnHeroClick = (MarvelHeroEntity, ImageView) -> Unit
typealias OnFavoriteClick = (MarvelHeroEntity) -> Unit

class HeroesListAdapter(val clickListener: OnHeroClick, val favoriteClickListener: OnFavoriteClick)
    : RecyclerView.Adapter<HeroesListAdapter.HeroesViewHolder>() {

    private lateinit var context: Context

    private var data: MutableList<MarvelHeroEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        context = parent.context!!
        return HeroesViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_hero, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<MarvelHeroEntity>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    inner class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MarvelHeroEntity) = with(itemView) {
            kotlin.with(itemView) {
                Glide.with(context)
                        .asBitmap()
                        .load(item.photoUrl)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                resource?.let { loadColorsFromBitmap(it) }
                                return false
                            }
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                        })
                        .into(heroImage)

                heroTitle.text = item.name
                setupFavoriteButton(favoriteButton, item)
                favoriteButton.setOnClickListener( {
                    item.favorite = item.favorite.not()
                    setupFavoriteButton(favoriteButton, item)
                    favoriteClickListener(item)
                })

                setOnClickListener { clickListener(item, heroImage) }
            }
        }

        fun loadColorsFromBitmap(bitmap: Bitmap) {
            with(itemView) {
                Palette.from(bitmap).generate { palette ->
                    val vibrant = palette.vibrantSwatch
                    vibrant?.let {
                        heroTitle.setBackgroundColor(vibrant.rgb)
                        heroTitle.setTextColor(vibrant.bodyTextColor)
                    }
                }
            }
        }

        private fun setupFavoriteButton(favoriteButton: ImageButton, hero: MarvelHeroEntity) {
            if(hero.favorite) {
                favoriteButton.setColorFilter(Color.argb(255, 251, 192, 2))
            } else {
                favoriteButton.setColorFilter(Color.argb(255, 255, 255, 255))
            }
        }

    }

}