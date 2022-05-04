package com.project.digicard.retrofit
import com.example.myapplication.GeneralResponce
import com.example.myapplication.ShowDatalist
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ApiService {

    @Headers("Authorization: tpbf49bdlr9202103tsptatps", "ShopID: 1")
    @GET(RestConstant.Datalist)
    fun showdata(): Call<List<ShowDatalist>>


    @Headers(
        "Authorization: tpbf49bdlr9202103tsptatps",
        "ShopID: 1",
        "Content-Type: application/json"
    )
    @POST(RestConstant.AddData)
    fun adduser(@Body text: JsonObject?): Call<GeneralResponce>


    @Headers(
        "Authorization: tpbf49bdlr9202103tsptatps",
        "ShopID: 1",
        "Content-Type: application/json"
    )
    @HTTP(method = "DELETE", path = RestConstant.AddData, hasBody = true)
    fun deleteuser(@Body text: JsonObject?): Call<GeneralResponce>


    @Headers(
        "Authorization: tpbf49bdlr9202103tsptatps",
        "ShopID: 1",
        "Content-Type: application/json"
    )

    @PUT(RestConstant.AddData)
    fun edituser(@Body text: JsonObject?): Call<GeneralResponce>


}