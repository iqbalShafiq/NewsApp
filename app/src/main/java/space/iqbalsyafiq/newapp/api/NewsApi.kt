package space.iqbalsyafiq.newapp.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import space.iqbalsyafiq.newapp.model.NewsResponse
import space.iqbalsyafiq.newapp.constant.ApiConstant.Companion.API_KEY

interface NewsApi {

    // Get News
    @GET("everything")
    fun getNews(
        @Header("X-Api-Key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("q") query: String = "apple",
    ): Single<NewsResponse>
}