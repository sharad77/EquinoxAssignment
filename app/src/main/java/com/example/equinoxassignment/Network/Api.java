package com.example.equinoxassignment.Network;

import com.example.equinoxassignment.Model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("betaDailyUpdateApi/Service1.svc/getManager")
    Call<List<DataModel>> getAllData();
}
