package br.com.okayamafilho.tcomposemealsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.okayamafilho.tcomposemealsapp.ui.detail.MealDetailScreen
import br.com.okayamafilho.tcomposemealsapp.ui.detail.MealDetailsViewModel
import br.com.okayamafilho.tcomposemealsapp.ui.meals.MealsCategoriesScreen
import br.com.okayamafilho.tcomposemealsapp.ui.theme.TComposeMealsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val viewModel by viewModels<MealCategoriesViewModel>()
        setContent {
            TComposeMealsAppTheme {
                FoodizApp()
            }
        }
    }
}

@Composable
private fun FoodizApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "destination_meals_list") {
        composable(route = "destination_meals_list") {
            MealsCategoriesScreen() {
                navigationMealId ->
                navController.navigate("destination_meals_list/$navigationMealId")
            }
        }

        composable(
            route = "destination_meals_list/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id")
            {
                type = NavType.StringType
            })
        ) {
            val viewModel: MealDetailsViewModel = viewModel()
                MealDetailScreen(viewModel.mealState.value)
        }
    }
}