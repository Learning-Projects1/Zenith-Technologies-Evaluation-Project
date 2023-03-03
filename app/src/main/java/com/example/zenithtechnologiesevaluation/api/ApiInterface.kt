package com.example.zenithtechnologiesevaluation.api

import com.example.zenithtechnologiesevaluation.presentation.messagesScreen.MessagesResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {


    @GET("sampleEndPoint")
    suspend fun getMessages(

    ):Response<MessagesResponseModel>

}