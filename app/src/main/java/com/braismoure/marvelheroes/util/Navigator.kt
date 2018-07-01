package com.braismoure.marvelheroes.util

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.View
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import com.braismoure.marvelheroes.presentation.heroedetail.MarvelHeroDetailActivity

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class Navigator {

    fun goToHeroDetail(activity: Activity, hero: MarvelHeroEntity, image: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, image,
                ViewCompat.getTransitionName(image))
        val intent = Intent(activity, MarvelHeroDetailActivity::class.java).apply {
            putExtra(MarvelHeroDetailActivity.PARAM_HERO, hero)
        }
        activity.startActivity(intent, options.toBundle())
    }

}