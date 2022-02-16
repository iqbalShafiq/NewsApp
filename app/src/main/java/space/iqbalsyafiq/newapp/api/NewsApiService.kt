package space.iqbalsyafiq.newapp.api

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import space.iqbalsyafiq.newapp.constant.ApiConstant.Companion.BASE_URL
import space.iqbalsyafiq.newapp.model.NewsResponse

class NewsApiService {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsApi::class.java)

    // Get top rated news
    fun getNews(page: Int = 1): Single<NewsResponse> {
        return api.getNews(page = page)
    }
}