package com.example.mealzapp.model.response

import com.google.gson.annotations.SerializedName

data class MealsCategoryResp(val categories: List<MealsResponse>)

data class MealsResponse(
    @SerializedName("idCategory") val id: String?,
    @SerializedName("strCategory") val name: String?,
    @SerializedName("strCategoryDescription") val description: String?,
    @SerializedName("strCategoryThumb") val imageUrl: String?
)