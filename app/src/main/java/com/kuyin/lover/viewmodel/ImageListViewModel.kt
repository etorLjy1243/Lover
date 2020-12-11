package com.kuyin.lover.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.kuyin.lover.model.ImageModel
import com.kuyin.lover.network.APIService
import com.kuyin.lover.network.RetroInterface

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListViewModel : ViewModel() {

    val mutableLiveData: MutableLiveData<ImageModel>

    init {
        mutableLiveData = MutableLiveData()
    }

    fun makeApiCall(page: Int, size: Int) {
        val apiService = RetroInterface.getRetrofitClient().create<APIService>(APIService::class.java!!)

        val call = apiService.getImageModel(page, size)

        call.enqueue(object : Callback<ImageModel> {
            override fun onResponse(call: Call<ImageModel>, response: Response<ImageModel>) {
                mutableLiveData.postValue(response.body())
            }


            override fun onFailure(call: Call<ImageModel>, t: Throwable) {
                mutableLiveData.postValue(null)
            }
        })

    }
}