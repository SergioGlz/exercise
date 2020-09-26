package dev.baires.exercise.core.configuration.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.baires.exercise.github.GithubFragment

@Module
interface FragmentsModule {

    @ContributesAndroidInjector
    fun contributeGithubFragment(): GithubFragment

}