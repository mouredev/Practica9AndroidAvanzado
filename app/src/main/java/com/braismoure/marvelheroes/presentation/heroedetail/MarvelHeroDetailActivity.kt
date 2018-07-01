package com.braismoure.marvelheroes.presentation.heroedetail

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import com.braismoure.marvelheroes.R
import com.braismoure.marvelheroes.data.model.MarvelHeroEntity
import com.braismoure.marvelheroes.presentation.MainApp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_hero_detail.*
import kotlinx.android.synthetic.main.item_hero.view.*
import javax.inject.Inject

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class MarvelHeroDetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_HERO = "hero"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var marvelHeroDetailViewModel: MarvelHeroDetailViewModel
    var marvelHeroEntity: MarvelHeroEntity? = null

    lateinit var rateButtons: List<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportPostponeEnterTransition() // Wait for image load and then draw the animation

        marvelHeroEntity = intent?.extras?.getParcelable(PARAM_HERO)
        init()
    }

    private fun inject() {
        (application as MainApp).component.inject(this)
    }

    private fun init() {
        marvelHeroDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(MarvelHeroDetailViewModel::class.java)
        fillHeroData()
    }

    private fun fillHeroData() {
        if (marvelHeroEntity == null) {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        onUserLoaded(marvelHeroEntity!!)
    }

    private fun onUserLoaded(hero: MarvelHeroEntity) {
        Glide.with(this)
                .load(hero.photoUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(heroDetailImage)

        heroDetailName.text = hero.name
        heroDetailRealName.text = hero.realName
        heroDetailHeight.text = hero.height
        heroDetailPower.text = hero.power
        heroDetailAbilities.text = hero.abilities

        setupFavoriteButton()
        favoriteButton.setOnClickListener( {
            marvelHeroEntity!!.favorite = marvelHeroEntity!!.favorite.not()
            setupFavoriteButton()
            marvelHeroDetailViewModel.updateHero(marvelHeroEntity!!)
        })

        rateButtons = arrayListOf(rateButton1, rateButton2, rateButton3, rateButton4, rateButton5)
        setupRateButtons()

        rateButton1.setOnClickListener {
            updateRate(1)
        }
        rateButton2.setOnClickListener {
            updateRate(2)
        }
        rateButton3.setOnClickListener {
            updateRate(3)
        }
        rateButton4.setOnClickListener {
            updateRate(4)
        }
        rateButton5.setOnClickListener {
            updateRate(5)
        }

        reviewText.setText(hero.comment)
        reviewText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                updateRateText(reviewText.text.toString())
                reviewText.clearFocus()
                true
            } else {
                false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupFavoriteButton() {
        if(marvelHeroEntity!!.favorite) {
            favoriteButton.setColorFilter(Color.argb(255, 251, 192, 2))
        } else {
            favoriteButton.setColorFilter(Color.argb(255, 255, 255, 255))
        }
    }

    private fun updateRate(rate: Int) {
        marvelHeroEntity!!.rate = rate
        setupRateButtons()
        marvelHeroDetailViewModel.updateHero(marvelHeroEntity!!)
    }

    private fun setupRateButtons() {
        rateButtons.forEachIndexed { index, button ->
            if (marvelHeroEntity!!.rate >= index + 1) {
                button.setColorFilter(Color.argb(255, 251, 192, 2))
            } else {
                button.setColorFilter(Color.argb(255, 255, 255, 255))
            }
        }
    }

    private fun updateRateText(text: String) {
        marvelHeroEntity!!.comment = text
        marvelHeroDetailViewModel.updateHero(marvelHeroEntity!!)
    }

}