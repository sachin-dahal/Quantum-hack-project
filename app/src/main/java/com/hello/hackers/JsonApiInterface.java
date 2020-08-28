package com.hello.hackers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonApiInterface {
    @POST("api/v1/oauth/token.json")
    Call<AccessToken> getToken(@Body ClienCredentials clienCredentials);

//    @Headers("Content-Type : application/json")
//    @GET("api/v1/search/medicines/brands/{medicine_query}")
//    Call<List<Medicine>> getMedicine(@Header("Authorization") String token, @Path("medicine_query") String name);

    @Headers("Content-Type:application/json")
    @GET("api/v1/search/medicines/brands/{medicine_query}")
    Call<List<Medicine>> getAllMedicines(@Header("Authorization")String token,
                                          @Path("medicine_query")String name);

    @Headers("Content-Type:application/json")
    @GET("api/v1/medicines/brands/{medicine_id}")
    Call<MedClick> getCurrentMedicine(@Header("Authorization")String token,
    @Path("medicine_id")String id);


}
