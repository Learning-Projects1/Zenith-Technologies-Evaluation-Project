package com.example.zenithtechnologiesevaluation.presentation.messagesScreen

import com.google.gson.annotations.SerializedName

data class MessagesResponseModel (

    @SerializedName("messages" ) var messages : ArrayList<Messages> = arrayListOf()

)

data class Messages (

    @SerializedName("Id"  ) var Id  : Int?    = null,
    @SerializedName("Msg" ) var Msg : String? = null

)