package dev.baires.exercise.github

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.baires.exercise.R
import dev.baires.exercise.core.repository.models.Projects
import javax.inject.Inject

class GithubViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
    private val loaderRx: MutableLiveData<Boolean>,
    private val errorMessageRx: MutableLiveData<Int>): ViewModel() {

    private val projectsLiveData: MutableLiveData<Projects> = MutableLiveData()

    fun getProjectsData(): LiveData<Projects> = projectsLiveData

    fun loading(): LiveData<Boolean> = loaderRx

    fun error(): LiveData<Int> = errorMessageRx

    @SuppressLint("CheckResult")
    fun getProjects() {
        githubRepository.getProjectsApi(loaderRx)
            .subscribe({
                projectsLiveData.postValue(it)
            }, {
                errorMessageRx.postValue(R.string.error_msg)
            })
    }

}