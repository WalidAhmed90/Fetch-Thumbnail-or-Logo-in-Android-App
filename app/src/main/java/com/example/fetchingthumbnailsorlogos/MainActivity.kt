package com.example.fetchingthumbnailsorlogos

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.fetchingthumbnailsorlogos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.apply {
            generateButton.setOnClickListener {
                val url = urlInput.text.toString()
                fetchThumbnail(url)
            }
        }

    }


    private fun fetchThumbnail(url: String) {
        mBinding.progress.visibility = View.VISIBLE
        val retrofit = Retrofit.Builder()
            .baseUrl("https://example.com/")  // Dummy base URL, will be overridden
            .client(OkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val html = apiService.fetchHtml(url)
                Log.d("HTML_CONTENT", html) // Log the fetched HTML content

                val document = Jsoup.parse(html)
                val metaTags = document.select("meta[property=og:image], meta[name=twitter:image]")

                var thumbnailUrl: String? = null
                for (metaTag in metaTags) {
                    val content = metaTag.attr("content")
                    if (content.isNotEmpty()) {
                        thumbnailUrl = content
                        break
                    }
                }

                // Fallback to Wikipedia's main image if no og:image or twitter:image found
                if (thumbnailUrl == null) {
                    val mainImage: Element? = document.select("table.infobox img").first()
                    mainImage?.let {
                        thumbnailUrl = "https:${it.attr("src")}"
                    }
                }

                // Fallback to domain logo if no thumbnail image found
                if (thumbnailUrl == null) {
                    val domain = Uri.parse(url).host ?: ""
                    thumbnailUrl = "https://logo.clearbit.com/$domain"
                }

                if (thumbnailUrl != null) {
                    launch(Dispatchers.Main) {
                        mBinding.progress.visibility = View.GONE
                        Glide.with(this@MainActivity)
                            .load(thumbnailUrl)
                            .into(mBinding.thumbnailImage)
                    }
                } else {
                    mBinding.progress.visibility = View.GONE
                    Log.d("THUMBNAIL", "No image found")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                mBinding.progress.visibility = View.GONE
            }
        }
    }
}