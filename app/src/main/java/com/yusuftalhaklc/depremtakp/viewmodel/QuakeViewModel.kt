package com.yusuftalhaklc.depremtakp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuftalhaklc.depremtakp.model.QuakeList
import com.yusuftalhaklc.depremtakp.model.Result
import com.yusuftalhaklc.depremtakp.service.QuakeAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuakeViewModel :ViewModel() {

    private val apiService = QuakeAPIService()

    var quakesLiveData = MutableLiveData<List<Result>>()
    var quakesLoading = MutableLiveData<Boolean>()
    var quakesError = MutableLiveData<Boolean>()

    fun getDataFromApi(){
        quakesLoading.value = true

        apiService.getQuakes()
            .enqueue(object : Callback<QuakeList> {
                override fun onResponse(
                    call: Call<QuakeList>,
                    response: Response<QuakeList>
                ) {
                    if(response.isSuccessful){
                        quakesLoading.value = false
                        quakesLiveData.value = response.body()?.result
                        Log.d("quake",response.body()?.result?.size.toString())
                    }
                    else{
                        quakesLoading.value = false
                        quakesError.value = true
                    }
                }

                override fun onFailure(call: Call<QuakeList>, t: Throwable) {
                    quakesError.value = true
                    quakesLoading.value = false
                }
            })

        Log.d("quakeL",quakesLoading.value.toString())
        Log.d("quakeE",quakesError.value.toString())
    }
}
