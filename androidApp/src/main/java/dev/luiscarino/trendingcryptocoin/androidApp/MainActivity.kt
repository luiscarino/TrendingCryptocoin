package dev.luiscarino.trendingcryptocoin.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import dev.luiscarino.trendingcryptocoin.shared.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.luiscarino.trendingcryptocoin.androidApp.adapter.CryptoListRvAdapter
import dev.luiscarino.trendingcryptocoin.shared.TrendingCryptoSDK
import dev.luiscarino.trendingcryptocoin.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBarView: FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val cryptoSdk = TrendingCryptoSDK(DatabaseDriverFactory(this))

    private val cryptoListAdapter = CryptoListRvAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun setView() {
        recyclerView = findViewById(R.id.trendingCryptoList)
        progressBarView = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)

        recyclerView.adapter = cryptoListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            displayTrendingCryptos(true)
        }

        displayTrendingCryptos(false)
    }

    private fun displayTrendingCryptos(needReload: Boolean) {
        progressBarView.isVisible = true
        mainScope.launch {
            kotlin.runCatching {
                cryptoSdk.getTrendingCryptoCoins()
            }.onSuccess {
                cryptoListAdapter.cryptos = it
                cryptoListAdapter.notifyDataSetChanged()
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            progressBarView.isVisible = false
        }
    }
}
