package com.braismoure.marvelheroes.di.modules

import android.app.Application
import android.content.Context
import com.braismoure.marvelheroes.util.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = Navigator()

}