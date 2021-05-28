package com.example.cryptocurrencyapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.models.CryptoCurrencyModel

class InfoFragment(var coinModel: CryptoCurrencyModel) : Fragment() {

    //TODO show data in UI from model

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }


}
