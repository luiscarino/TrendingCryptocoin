package dev.luiscarino.trendingcryptocoin.shared.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoCoin(
    @SerialName("coin_id")
    val id: String,
    @SerialName("coin_symbol")
    val symbol: String,
    @SerialName("coin_name")
    val name: String,
    @SerialName("coin_image")
    val image: Image,
    @SerialName("coin_links")
    val links: Links,
    @SerialName("price")
    val price: Price
)

@Serializable
data class Links(
    @SerialName("link_homepage")
    val homepageUrl: String?,
    @SerialName("link_blockchain_site")
    val blockchainUrl: String?,
    @SerialName("link_twitter_username")
    val twitterUserName: String?,
    @SerialName("link_subreddit")
    val subredditUrl: String?,
    @SerialName("link_github")
    val githubUrl: String?
)

@Serializable
data class Image(
    @SerialName("image_thumb")
    val thumbUrl: String?,
    @SerialName("image_small")
    val smallUrl: String?,
    @SerialName("image_large")
    val largeUrl: String?,
)

@Serializable
data class Price(
    @SerialName("current_price")
    val currentPrice: Double,
    @SerialName("all_time_high")
    val allTimeHigh: Double,
    @SerialName("all_time_low")
    val allTimeLow: Double,
    @SerialName("price_change_24h")
    val priceChange24h: Double,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,
    @SerialName("market_cap")
    val marketCap: Double,
    @SerialName("total_volume")
    val totalVolume: Double,
    @SerialName("high_24h")
    val high24h: Double,
    @SerialName("low_24h")
    val low24h: Double,
    @SerialName("total_supply")
    val totalSupply: Double,
    @SerialName("circulating_supply")
    val circulatingSupply: Double,
    @SerialName("last_updated")
    val lastUpdated: String
)