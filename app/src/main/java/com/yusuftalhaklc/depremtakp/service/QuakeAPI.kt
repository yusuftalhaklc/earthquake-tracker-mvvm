package com.yusuftalhaklc.depremtakp.service

import com.yusuftalhaklc.depremtakp.model.QuakeList
import com.yusuftalhaklc.depremtakp.model.Result
import retrofit2.Call
import retrofit2.http.GET

interface QuakeAPI {

    @GET("/deprem/live.php?limit=100")
    fun quakes() : Call<QuakeList>
}