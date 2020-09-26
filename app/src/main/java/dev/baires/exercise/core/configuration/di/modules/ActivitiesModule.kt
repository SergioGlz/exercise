package dev.baires.exercise.core.configuration.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.baires.exercise.core.view.MainActivity

@Module
interface ActivitiesModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

}