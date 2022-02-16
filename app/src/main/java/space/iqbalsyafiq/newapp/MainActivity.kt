package space.iqbalsyafiq.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import space.iqbalsyafiq.newapp.adapters.HorizontalNewsAdapter
import space.iqbalsyafiq.newapp.adapters.VerticalNewsAdapter
import space.iqbalsyafiq.newapp.databinding.ActivityMainBinding
import space.iqbalsyafiq.newapp.model.Article
import space.iqbalsyafiq.newapp.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get news
        viewModel.getNews()

        // on refresh
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getNews()
            binding.swipeRefresh.isRefreshing = false
        }

        // observe live data
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                with(binding) {
                    swipeRefresh.isRefreshing = isLoading
                }
            }
        }

        viewModel.loadError.observe(this) { isError ->
            isError?.let {
                with(binding) {
                    tvError.visibility = if (isError) View.VISIBLE else View.GONE
                    llContainer.visibility = if (!isError) View.VISIBLE else View.GONE
                }
            }
        }

        viewModel.news.observe(this) { newsList ->
            newsList?.let {
                with(binding) {
                    rvHorizontalNews.adapter = HorizontalNewsAdapter(
                        this@MainActivity,
                        newsList as ArrayList<Article>
                    )
                    rvVertical.adapter = VerticalNewsAdapter(
                        this@MainActivity,
                        newsList
                    )
                }
            }
        }
    }
}