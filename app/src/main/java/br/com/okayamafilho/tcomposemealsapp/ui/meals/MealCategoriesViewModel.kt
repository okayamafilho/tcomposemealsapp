package br.com.okayamafilho.tcomposemealsapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.okayamafilho.tcomposemealsapp.model.MealsRepository
import br.com.okayamafilho.tcomposemealsapp.model.response.MealResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {

    //    private val mealsJob = Job()
    val mealsState: MutableState<List<MealResponse>> =
        mutableStateOf(emptyList<MealResponse>())

    init {
//        val scope = CoroutineScope(mealsJob + Dispatchers.IO)
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    private suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }

//    override fun onCleared() {
//        super.onCleared()
//        mealsJob.cancel()
//    }
}