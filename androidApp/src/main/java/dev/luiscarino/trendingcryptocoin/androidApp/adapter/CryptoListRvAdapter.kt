package dev.luiscarino.trendingcryptocoin.androidApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.luiscarino.trendingcryptocoin.androidApp.R
import dev.luiscarino.trendingcryptocoin.shared.cache.Cryptocoin

class CryptoListRvAdapter(var cryptos: List<Cryptocoin>)
    : RecyclerView.Adapter<CryptoListRvAdapter.CryptoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoItemViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cryptocoin, parent, false)
            .run(::CryptoItemViewHolder)
    }

    override fun onBindViewHolder(holder: CryptoItemViewHolder, position: Int) {
        holder.bindData(cryptos[position])
    }

    override fun getItemCount(): Int = cryptos.size

    inner class CryptoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.cryptoName)
        fun bindData(crypto: Cryptocoin) {
            name.text = crypto.name
        }
    }

}