package dev.luiscarino.trendingcryptocoin.shared

import dev.luiscarino.trendingcryptocoin.shared.cache.Cryptocoin
import dev.luiscarino.trendingcryptocoin.shared.cache.Database
import dev.luiscarino.trendingcryptocoin.shared.cache.DatabaseDriverFactory
import dev.luiscarino.trendingcryptocoin.shared.entity.Coin
import dev.luiscarino.trendingcryptocoin.shared.entity.CryptoCoin
import dev.luiscarino.trendingcryptocoin.shared.network.CoinGeckoApi

/**
 * Our iOS and Android applications will communicate with the trending crypto coins API through our KMM Module,
 * which will provide a public TrendingCryptoSDK class.
 * It will be the facade over Database and the network classes.
 */
class TrendingCryptoSDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = Database(databaseDriverFactory)
    private val api = CoinGeckoApi()

    /**
     * Fetches trending crypto coins information.
     * Depending on the value of forceReload it will return cached values or load data from the internet
     * and then update the cache with the results.
     * If there is no cached data,
     * it will load information from the internet independently of forceReloadflag value:
     */
    @Throws(Exception::class)
    suspend fun getTrendingCryptoCoins(): List<Cryptocoin> {
        val cachedCryptoCoins = database.getTrendingCryptocoins()
        return if (cachedCryptoCoins.isNotEmpty()) {
            cachedCryptoCoins
        } else {
            api.getTrendingCoins().coins
                .map { it.toCryptocoin()}
                .toList()
        }
    }
}

fun Coin.toCryptocoin() = Cryptocoin(
    id = item.id,
    symbol = item.symbol,
    name = item.name,
    currentPrice = .0
)