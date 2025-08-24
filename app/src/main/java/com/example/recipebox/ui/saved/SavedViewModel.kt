package com.example.recipebox.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebox.domain.usecase.GetCollectionUseCase
import com.example.recipebox.domain.usecase.GetCollectionImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CollectionItem(
    val id: Long,
    val title: String,
    val imageUrl: String
)

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getCollectionUseCase: GetCollectionUseCase,
    private val getCollectionImageUseCase: GetCollectionImageUseCase
) : ViewModel() {

    private val _collections = MutableStateFlow<List<CollectionItem>>(emptyList())
    val collections: StateFlow<List<CollectionItem>> = _collections.asStateFlow()

    init {
        loadCollections()
    }

    private fun loadCollections() {
        viewModelScope.launch {
            getCollectionUseCase().collect { list ->
                val items = list.map { collection ->
                    val imageUrl = getCollectionImageUseCase(collection.id) ?: ""
                    CollectionItem(
                        id = collection.id,
                        title = collection.name,
                        imageUrl = imageUrl
                    )
                }
                _collections.value = items
            }
        }
    }
}
