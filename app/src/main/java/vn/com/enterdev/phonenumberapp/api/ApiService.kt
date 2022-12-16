package vn.com.enterdev.phonenumberapp.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/?results=10")
    fun getData() : Call<vn.com.enterdev.phonenumberapp.model.Contacts>
}