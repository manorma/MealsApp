package com.example.mealzapp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.model.response.MealsCategoryResp
import com.example.mealzapp.model.response.MealsResponse
import com.example.mealzapp.ui.details.MealDetailsScreen
import com.example.mealzapp.ui.details.MealDetailsViewModel
import com.example.mealzapp.ui.theme.MealzAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                MealsApp()
            }
        }
    }

    @Composable
    private fun MealsApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "meals_list") {
            composable(route = "meals_list") {
                MealCategoryScreen() { mealId ->
                    navController.navigate(route = "meal_details/$mealId")
                }
            }
            composable(
                route = "meal_details/{meal_category_id}",
                arguments = listOf(navArgument("meal_category_id") {
                    type = NavType.StringType
                })
            ) {
                val viewModel: MealDetailsViewModel = viewModel()
                MealDetailsScreen(meals = viewModel.mealState.value)
            }
        }

    }
}

