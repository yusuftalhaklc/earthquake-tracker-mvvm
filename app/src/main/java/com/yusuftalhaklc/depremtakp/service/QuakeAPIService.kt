package com.yusuftalhaklc.depremtakp.service

import com.yusuftalhaklc.depremtakp.model.QuakeList
import com.yusuftalhaklc.depremtakp.model.Result
import com.yusuftalhaklc.depremtakp.utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuakeAPIService {

    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuakeAPI::class.java)

    fun getQuakes() : Call<QuakeList> {
        return api.quakes()
    }
}