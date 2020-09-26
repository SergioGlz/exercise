package dev.baires.exercise.core.configuration.di.modules

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FeaturesModule {

    @Provides
    @Singleton
    fun loaderRx(): MutableLiveData<Boolean> = MutableLiveData()

    @Provides
    @Singleton
    fun errorMessageRx(): MutableLiveData<Int> = MutableLiveData()

}