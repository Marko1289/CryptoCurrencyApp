package com.example.cryptocurrencyapp

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cryptocurrencyapp.adapters.RecyclerAdapter
import com.example.cryptocurrencyapp.models.CryptoCurrencyModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MAIN_ACTIVITY"


    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private lateinit var coinList: MutableList<CryptoCurrencyModel>
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coinList = mutableListOf()
        setAdapter()
        getListFromServer()

    }

    private fun setAdapter() {
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler = findViewById(R.id.list_recycler_view)
        recycler.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(coinList, this)
        recycler.adapter = adapter

    }

    // Request call
    private fun getListFromServer() {


        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle(R.string.loading_data)
        progressDialog.show()

        //TODO get data from server and set it by SortOrder
        val queue = Volley.newRequestQueue(this)
        val gson = Gson()

        val stringRequest = StringRequest(Request.Method.GET, Commons.CRYPTO_CURRENCY_LIST,
            { response ->

                val jsonObject = JSONObject(response.toString())
                if (jsonObject.getString("Response").equals("Success")) {
                    val data = jsonObject.getJSONObject("Data".trim())

                    val dataKeys = data.keys()
                    while (dataKeys.hasNext()) {
                        val key = dataKeys.next()
                        if (data[key] is JSONObject) {
                            val coin =
                                gson.fromJson(data[key].toString(), CryptoCurrencyModel::class.java)
                            coinList.add(coin)
                        }
                    }
                    coinList.sortWith(compareBy { it.SortOrder?.toInt() })
                    recycler.adapter?.notifyDataSetChanged()
                }
                progressDialog.hide()

            }, {
                progressDialog.hide()
                error(TAG)

            })
        queue.add(stringRequest)
    }
}