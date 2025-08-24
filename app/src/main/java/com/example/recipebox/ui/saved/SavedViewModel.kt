package com.example.recipebox.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebox.domain.model.Collection
import com.example.recipebox.domain.repository.CollectionRepository
import com.example.recipebox.domain.usecase.GetCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SavedState {
    object Loading : SavedState()
    data class Success(val collections: List<Collection>) : SavedState()
    data class Error(val error: String) : SavedState()
}

@HiltViewModel
class SavedViewModel @Inject constructor(private val getCollectionUseCase: GetCollectionUseCase
) : ViewModel() {
    private val _savedState = MutableStateFlow<SavedState>(SavedState.Loading)
    val savedState: StateFlow<SavedState> = _savedState

    fun getCollections() {
        viewModelScope.launch {
            _savedState.value = SavedState.Loading
            try {
                getCollectionUseCase().collect { collections ->
                    _savedState.value = SavedState.Success(collections)
                }
            } catch (e: Exception) {
                _savedState.value = SavedState.Error("Failed to load collections: ${e.message}")
            }
        }
    }
}