package com.braismoure.marvelheroes.presentation

import android.app.Application
import com.braismoure.marvelheroes.di.components.ApplicationComponent
import com.braismoure.marvelheroes.di.components.DaggerApplicationComponent
import com.braismoure.marvelheroes.di.modules.ApplicationModule

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
class MainApp : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}