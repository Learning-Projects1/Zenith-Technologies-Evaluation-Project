package com.example.zenithtechnologiesevaluation.presentation.messagesScreen

import com.example.zenithtechnologiesevaluation.api.ApiInterface
import com.example.zenithtechnologiesevaluation.api.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MessagesRepository @Inject constructor(private val apiInterface: ApiInterface){


    suspend fun getMessages(): Flow<Resources<MessagesResponseModel>> {
        return flow {
            try{
                val response = apiInterface.getMessages()
                if (response.isSuccessful && response.code() == 200) {
                    emit(Resources.Success(response.body()))
                } else{
                    emit(Resources.Error("Something went wrong!"))
                }


            }catch (e: Exception){
                emit(Resources.Error(e.toString()))
            }
        }
    }

}