package com.example.mealzapp.model.api

import com.example.mealzapp.model.response.MealsCategoryResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MealsWebService {
    private lateinit var mealsApi: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mealsApi = retrofit.create(MealsApi::class.java)
    }

    suspend fun getMeals(): MealsCategoryResp { //asycn func
        return mealsApi.getMeals()
    }

    interface MealsApi {
        @GET("categories.php")
        suspend fun getMeals(): MealsCategoryResp //asycn func
    }
}