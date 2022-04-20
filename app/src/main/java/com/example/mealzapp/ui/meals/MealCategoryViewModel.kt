package com.example.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.model.MealsRepository
import com.example.mealzapp.model.response.MealsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealCategoryViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
    val mealsState: MutableState<List<MealsResponse>> = mutableStateOf(emptyList())
    //custom coroutine
//    private val mealJob = Job()
//    val scope = CoroutineScope(mealJob + Dispatchers.IO)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }

    }

    suspend fun getMeals(): List<MealsResponse> {
        return repository.getMeals().categories
    }
}