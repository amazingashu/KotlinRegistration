package com.acrony.kotlinregistration

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterServiceForm {
    @FormUrlEncoded
    @POST("userRegistration.php")
    fun getStringScalar(
        @Field("Name") Name:String,
        @Field("Email") Email:String,
        @Field("Phone") Phone:String,
        @Field("Password") Password:String
    ):Call<String>
}

