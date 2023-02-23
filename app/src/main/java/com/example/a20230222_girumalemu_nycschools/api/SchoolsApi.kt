package com.example.a20230222_girumalemu_nycschools.api

import com.example.a20230222_girumalemu_nycschools.api.ApiUtil.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchoolsApi {

    companion object {
        private var INSTANCE: Retrofit? = null

        fun getInstance(): SchoolsService {
            var tmp = INSTANCE

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    tmp = INSTANCE
                }
            }

            return tmp!!.create(SchoolsService::class.java)
        }

    }
}

object ApiUtil {
    const val BASE_URL: String = "https://data.cityofnewyork.us/resource/"
}