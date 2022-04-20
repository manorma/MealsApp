package com.example.mealzapp.model

import com.example.mealzapp.model.api.MealsWebService
import com.example.mealzapp.model.response.MealsCategoryResp
import com.example.mealzapp.model.response.MealsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    private var cachedMeals = listOf<MealsResponse>()
    suspend fun getMeals(): MealsCategoryResp {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMealDetails(id: String) : MealsResponse?{
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }
//        webService.getMeals().enqueue(object :
//            Callback<MealsCategoryResp> {
//            override fun onResponse(
//                call: Call<MealsCategoryResp>,
//                response: Response<MealsCategoryResp>
//            ) {
//                if (response.isSuccessful) {
//                    successCallback(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<MealsCategoryResp>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })

    companion object{
        @Volatile
        private var instance : MealsRepository? =null
        fun getInstance() = instance ?: synchronized(this){
            instance ?: MealsRepository().also { instance = it }
        }
    }


}