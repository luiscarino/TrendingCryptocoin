package dev.luiscarino.trendingcryptocoin.shared


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
