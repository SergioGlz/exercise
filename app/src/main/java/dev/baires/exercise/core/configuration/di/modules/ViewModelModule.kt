package dev.baires.exercise.core.configuration.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.baires.exercise.core.configuration.architecture.ViewModelFactory
import dev.baires.exercise.core.configuration.architecture.ViewModelKey
import dev.baires.exercise.github.GithubViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GithubViewModel::class)
    internal abstract fun githubViewModel(viewModel: GithubViewModel): ViewModel

}