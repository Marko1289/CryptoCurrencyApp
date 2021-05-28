package com.example.cryptocurrencyapp.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.activities.InfoAndDiagramActivity
import com.example.cryptocurrencyapp.models.CryptoCurrencyModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class RecyclerAdapter(var coinList: MutableList<CryptoCurrencyModel>, val activity: Activity) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.cryptocurrency_list_row, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemName.text = coinList.get(position).CoinName
        holder.itemSymbol.text = coinList.get(position).Symbol

        val url = "https://www.cryptocompare.com${coinList[position].ImageUrl}"
        Picasso.with(activity).load(url).into(holder.itemImage)
        print(url)
        holder.itemLayout.setOnClickListener {
            val intent = Intent(activity, InfoAndDiagramActivity::class.java)
            intent.putExtra("coinModel", coinList[position])
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if (coinList.size > 0) {
            coinList.size
        } else
            0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView = itemView.findViewById(R.id.crypto_image)
        var itemName: TextView = itemView.findViewById(R.id.crypto_name)
        var itemSymbol: TextView = itemView.findViewById(R.id.crypto_symbol)
        var itemLayout: CardView = itemView.findViewById(R.id.crypto_list_layout)

    }
}