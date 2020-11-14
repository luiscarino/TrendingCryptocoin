package dev.luiscarino.trendingcryptocoin.shared.cache

import com.squareup.sqldelight.db.SqlDriver

/**
 * SQLDelight provides multiple platform-specific implementations of the SQLite driver,
 * so we need to create them for each platform separately.
 */
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}