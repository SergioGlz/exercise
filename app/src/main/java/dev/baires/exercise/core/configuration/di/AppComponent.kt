package dev.baires.exercise.core.configuration.di

import androidx.lifecycle.MutableLiveData
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dev.baires.exercise.core.application.ExampleApplication
import dev.baires.exercise.core.configuration.di.modules.*
import dev.baires.exercise.core.repository.Api
import dev.baires.exercise.github.GithubRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [FeaturesModule::class, NetworkModule::class, AndroidInjectionModule::class, ActivitiesModule::class, FragmentsModule::class, ViewModelModule::class])
interface AppComponent: AndroidInjector<ExampleApplication> {
    fun api(): Api
    fun loaderRx(): MutableLiveData<Boolean>
    fun errorMessageRx(): MutableLiveData<Int>
    fun githubRepository(): GithubRepository
}