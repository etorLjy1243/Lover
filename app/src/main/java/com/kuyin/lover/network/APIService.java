package com.kuyin.lover.network;

import com.kuyin.lover.model.ImageModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("api/v2/data/category/Girl/type/Girl/page/{page}/count/{size}")
    Call<ImageModel> getImageModel(@Path("page") int page, @Path("size") int size);
}
