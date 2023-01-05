package com.owen.quakealert_owen.retrofit

import com.owen.quakealert_owen.model.Data
import com.owen.quakealert_owen.model.SubmitComment
import com.owen.quakealert_owen.model.SubmitLogin
import com.owen.quakealert_owen.model.SubmitRegister
import com.owen.quakealert_owen.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface EndPointApi {
//    @GET("autogempa.json")
//    suspend fun getGempaTerbaru(
//    ):Response <Gempa>
    @GET("gempaterkini")
     suspend fun getGempaData():Response<Data>


     @GET("gempahis")
        suspend fun getGempaHistory():Response<GempaHistory>

    //insert gempa
    @FormUrlEncoded
    @POST("gempahis")
    fun insertGempa(
        @Field("wilayah") wilayah: String,
        @Field("tanggal") tanggal: String,
        @Field("magnitudo") magnitudo: String,
    ):Call<SubmitGempa>

    @GET("gempa")
     suspend fun getGempaDirasakan():Response<GempaDirasakan>

     //get comment
    @GET("comment")
    suspend fun getComment():Response<Comment>

    //Get User
    @GET("login/{id}")
    suspend fun getUserbyId(
        @Path("id") id : Int
    ):Response<Users>

    //Insert Data
    @FormUrlEncoded
    @POST("comment")
     fun insertComment(
        @Field("comment") comment:String,
        @Field("user_id") user_id:String
    ):Call<SubmitComment>



    //Insert User
    @FormUrlEncoded
    @POST("register")
    fun insertUser(
        @Field("name") name:String,
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("email") email:String,
        @Field("status") status:String,
        @Field("image") image:String
    ):Call<SubmitRegister>

    //Update User
    @FormUrlEncoded
    @PATCH("update")
    fun updateUser(
        @Field("id") id:Int,
        @Field("name") name:String,
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("email") email:String,
        @Field("status") status:String,
        @Field("image") image:String
    ):Call<SubmitRegister>

    //Login User
    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<SubmitLogin>

    //delete Comment
    @DELETE("comment/{id}")
    fun deleteComment(
        @Path("id") id:Int
    ):Call<SubmitComment>


}