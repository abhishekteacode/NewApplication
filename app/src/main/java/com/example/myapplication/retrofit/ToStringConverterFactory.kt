package com.project.digicard.retrofit

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ToStringConverterFactory {
    private val MEDIA_TYPE = "text/plain".toMediaTypeOrNull()

    fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody?, *>? {
        return if (String::class.java == type) {
            Converter { value -> value }
        } else null
    }

    fun stringConverter(
        type: Type,
        annotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): Converter<*, String?>? {
        return if (String::class.java == type) {
            Converter<String?, String?> { value -> value }
        } else null
    }
}
