package com.tufanakcay.androidplayer.services

import com.tufanakcay.androidplayer.models.UserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUser {

    @GET("api/")
    fun allUser( @Query("results") results:Int ) : Call<UserData>;

}