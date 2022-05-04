package com.project.digicard.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object RetrofitRestClient {

    private val TIME = 120
    private var baseApiService: ApiService? = null
    private var httpClient: OkHttpClient? = null


    private fun getHttpClient(): OkHttpClient? {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        try {
            httpClient = OkHttpClient().newBuilder()
                .connectTimeout(TIME.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIME.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIME.toLong(), TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addNetworkInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                    val request = requestBuilder.build()
                    val response = chain.proceed(request)
                        //Log.w("Response_Body", "" + Gson().toJson(response.body()))
                    response
                }
                .build()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return httpClient
    }

    var gson = GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
        .excludeFieldsWithoutExposeAnnotation()
        .serializeNulls()
        .create()


    fun getInstance(): ApiService? {
        if (baseApiService == null) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(RestConstant.BASE_URL)
                //.addConverterFactory(ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getHttpClient())
                .build()
            baseApiService = retrofit.create(ApiService::class.java)
        }
        return baseApiService
    }

}