package org.mrwolfedev.insightspark.presentation.NewsViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolf.news.domain.useCase.NewsUseCase.Headline.HeadlineRepositoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.mrwolfedev.insightspark.data.mapper.toDomain
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem
import org.mrwolfedev.insightspark.domain.util.Result

// ✅ UI state holding both Trending and Category News separately
data class NewsUiState(
    val isLoading: Boolean = false,
    val trendingNews: List<ShowNewsItem>? = null,
    val categoryNews: List<ShowNewsItem>? = null,
    val error: String? = null
)

class NewsViewModel(
    private val headlineUseCase: HeadlineRepositoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    private val _categoryNewsState = MutableStateFlow<Result<List<ShowNewsItem>>>(Result.Idle)
    val categoryNewsState: StateFlow<Result<List<ShowNewsItem>>> = _categoryNewsState.asStateFlow()

    var selectedCategory by mutableStateOf("general")
        private set

    fun onCategorySelected(category: String) {
        selectedCategory = category
        getHeadlinesByCategory("us", category)
    }

    fun loadTrendingNews(country: String = "us") {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            when (val result = headlineUseCase.getByCountry(country)) {
                is Result.Success -> {
                    val articles = result.data?.articles?.map { it.toDomain() } ?: emptyList()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        trendingNews = articles,
                        error = null
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        trendingNews = null,
                        error = result.message
                    )
                }
                is Result.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                }
                is Result.Idle -> {
                    // No change
                }
            }
        }
    }

    fun getHeadlinesByCategory(country: String, category: String) {
        viewModelScope.launch {
            _categoryNewsState.value = Result.Loading

            when (val result = headlineUseCase.getByCategory(country, category)) {
                is Result.Success -> {
                    val articles = result.data?.articles?.map { it.toDomain() } ?: emptyList()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        categoryNews = articles,
                        error = null
                    )
                    _categoryNewsState.value = Result.Success(articles)
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        categoryNews = null,
                        error = result.message
                    )
                    _categoryNewsState.value = Result.Error(result.message)
                }
                is Result.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                    _categoryNewsState.value = Result.Loading
                }
                is Result.Idle -> {
                    _categoryNewsState.value = Result.Idle
                }
            }
        }
    }

    fun getHeadlinesByCountry(country: String) {
        loadTrendingNews(country)
    }
}
