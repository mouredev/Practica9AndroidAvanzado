package com.braismoure.marvelheroes.util.mvvm

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by MoureDev by Brais Moure on 30/6/18.
 * www.mouredev.com
 */
abstract class BaseViewModel : ViewModel() {

    internal val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}