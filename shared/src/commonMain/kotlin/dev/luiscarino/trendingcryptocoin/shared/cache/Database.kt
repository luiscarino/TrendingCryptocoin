package dev.luiscarino.trendingcryptocoin.shared.cache

/**
 *  Wrapper for the AppDatabase class that contains caching logic.
 */
internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    /**
     * Deletes all tables in the database
     */
    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllCryptoCoins()
        }
    }

    /**
     * Get a list of trending cryptocoins
     */
    internal fun getTrendingCryptocoins(): List<Cryptocoin> {
        return dbQuery.selectAllCryptocoins().executeAsList()
    }

    /**
     * Insert new cryptocoin
     */
    internal fun insertCryptocoin(id: String, name:String, symbol:String, price:Double) {
        dbQuery.transaction {
            dbQuery.insertCryptocoin(id, name, symbol, price)
        }
    }
}