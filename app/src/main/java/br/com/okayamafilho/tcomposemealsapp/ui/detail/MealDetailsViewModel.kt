package br.com.okayamafilho.tcomposemealsapp.ui.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.okayamafilho.tcomposemealsapp.model.MealsRepository
import br.com.okayamafilho.tcomposemealsapp.model.response.MealResponse

class MealDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val respository: MealsRepository = MealsRepository.getInstance()

    var mealState = mutableStateOf<MealResponse?>(null)

    init {
        val mealId = savedStateHandle.get<String>("meal_category_id") ?: ""
        mealState.value = respository.getMeal(mealId)

    }
}