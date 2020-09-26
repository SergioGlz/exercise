package dev.baires.exercise.github

import io.reactivex.Observable
import androidx.lifecycle.MutableLiveData
import dev.baires.exercise.core.repository.Api
import dev.baires.exercise.core.repository.models.Projects
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GithubRepository @Inject constructor(val api: Api) {

    fun getProjectsApi(loaderRx: MutableLiveData<Boolean>): Observable<Projects> = api
        .getProjects("android+language:kotlin")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { loaderRx.postValue(true) }
        .doOnTerminate { loaderRx.postValue(false) }

}