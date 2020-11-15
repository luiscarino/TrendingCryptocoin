package dev.luiscarino.trendingcryptocoin.shared.network

import dev.luiscarino.trendingcryptocoin.shared.entity.TrendingCoinGecko
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

/**
 * Class to interact with the CoinGecko API.
 * This class is responsible for executing network requests and deserialize JSON responses
 * into entities from the entity package.
 *
 */
class CoinGeckoApi {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getTrendingCoins(): TrendingCoinGecko {
        return httpClient.get(TRENDING_COINS_ENDPOINT)
    }

    companion object {
        private const val TRENDING_COINS_ENDPOINT = "https://api.coingecko.com/api/v3/search/trending"
    }
}