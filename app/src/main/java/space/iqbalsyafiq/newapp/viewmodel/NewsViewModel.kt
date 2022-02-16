package space.iqbalsyafiq.newapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import space.iqbalsyafiq.newapp.api.NewsApiService
import space.iqbalsyafiq.newapp.model.Article
import space.iqbalsyafiq.newapp.model.NewsResponse

class NewsViewModel(application: Application) : BaseViewModel(application) {

    private val service = NewsApiService()
    private val disposable = CompositeDisposable()

    // live data
    val news = MutableLiveData<List<Article>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun getNews() {
        loading.value = true

        disposable.add(
            service.getNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewsResponse>() {
                    override fun onSuccess(newsResponse: NewsResponse) {
                        loading.value = false
                        news.value = newsResponse.articles
                    }

                    override fun onError(e: Throwable) {
                        loadError.value = true
                        loading.value = false
                        Log.d("NewsVM", "onError: $e")
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}