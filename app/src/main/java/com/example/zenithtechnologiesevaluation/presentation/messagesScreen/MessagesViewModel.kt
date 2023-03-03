package com.example.zenithtechnologiesevaluation.presentation.messagesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zenithtechnologiesevaluation.api.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(private val repository: MessagesRepository) : ViewModel() {

    private val _state = MutableStateFlow<StateController>(StateController.Rest)
    val state: StateFlow<StateController> = _state

    sealed class StateController{
        object Loading : StateController()
        object Rest : StateController()
        object NoDataFound : StateController()
        data class Error(val message : String) : StateController()
        data class Success(val data : MessagesResponseModel?): StateController()
    }



    fun getProductsListApiCall(){
        viewModelScope.launch {

            _state.value = StateController.Loading

            repository.getMessages().collect{ response ->
                when(response){


                    is Resources.Success ->{
                        _state.value = StateController.Success(response.data)
                    }

                    is Resources.Error ->{
                        _state.value = StateController.Error(response.message.toString())
                    }

                    else -> Unit
                }
            }

        }
    }



}