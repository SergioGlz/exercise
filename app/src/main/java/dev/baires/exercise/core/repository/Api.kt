package dev.baires.exercise.core.repository

import dev.baires.exercise.BuildConfig
import dev.baires.exercise.core.repository.models.Projects
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search/repositories")
    fun getProjects(@Query("q", encoded = true) query: String, @Query("sort") sort: String = "stars", @Query("order") order: String = "desc"): Observable<Projects>

    companion object {
        fun create(): Api {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(BuildConfig.GITHUB_API_URL)
                .build()
            return retrofit.create(Api::class.java)
        }
    }

}