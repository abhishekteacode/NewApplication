package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GeneralResponce {
    @SerializedName("StringerID")
    @Expose
    private var stringerID: Int? = null

    fun getStringerID(): Int? {
        return stringerID
    }

    fun setStringerID(stringerID: Int?) {
        this.stringerID = stringerID
    }
}