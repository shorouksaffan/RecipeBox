package com.example.recipebox.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebox.domain.model.Collection
import com.example.recipebox.domain.usecase.GetCollectionImageUseCase
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
class SavedViewModel @Inject constructor(private val getCollectionUseCase: GetCollectionUseCase, private val getCollectionImageUseCase: GetCollectionImageUseCase
) : ViewModel() {
    private val _savedState = MutableStateFlow<SavedState>(SavedState.Loading)
    val savedState: StateFlow<SavedState> = _savedState

    fun getCollections() {
        viewModelScope.launch {
            _savedState.value = SavedState.Loading
            try {
                getCollectionUseCase().collect { collections ->
                    val collectionsWithImages = collections.map { collection ->
                        val imageUrl = getCollectionImageUseCase(collection.id)
                        collection.copy(imageUrl = imageUrl)
                    }
                    _savedState.value = SavedState.Success(collectionsWithImages)
                }
            } catch (e: Exception) {
                _savedState.value = SavedState.Error("Failed to load collections: ${e.message}")
            }
        }
    }
}