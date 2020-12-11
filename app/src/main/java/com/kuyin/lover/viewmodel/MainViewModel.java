package com.kuyin.lover.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuyin.lover.model.ImageModel;
import com.kuyin.lover.network.APIService;
import com.kuyin.lover.network.RetroInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    public MutableLiveData<ImageModel> mutableLiveData = new MutableLiveData<>();

    public void makeApiCall(int page, int size){

        APIService retroInstance =  RetroInterface.getRetrofitClient().create(APIService.class);
        Call<ImageModel> call = retroInstance.getImageModel(page,size);
        call.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {
                mutableLiveData.postValue(null);
            }
        });
    }

}
