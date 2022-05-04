package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ShowDatalist {
    @SerializedName("Address")
    @Expose
    private var address: String? = null

    @SerializedName("Age")
    @Expose
    private var age: Int? = null

    @SerializedName("CloseTiming")
    @Expose
    private var closeTiming: String? = null

    @SerializedName("Name")
    @Expose
    private var name: String? = null

    @SerializedName("Password")
    @Expose
    private var password: String? = null

    @SerializedName("PhoneNumber")
    @Expose
    private var phoneNumber: String? = null

    @SerializedName("StartTiming")
    @Expose
    private var startTiming: String? = null

    @SerializedName("StringerID")
    @Expose
    private var stringerID: Int? = null

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String?) {
        this.address = address
    }

    fun getAge(): Int? {
        return age
    }

    fun setAge(age: Int?) {
        this.age = age
    }

    fun getCloseTiming(): String? {
        return closeTiming
    }

    fun setCloseTiming(closeTiming: String?) {
        this.closeTiming = closeTiming
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }

    fun getStartTiming(): String? {
        return startTiming
    }

    fun setStartTiming(startTiming: String?) {
        this.startTiming = startTiming
    }

    fun getStringerID(): Int? {
        return stringerID
    }

    fun setStringerID(stringerID: Int?) {
        this.stringerID = stringerID
    }
}