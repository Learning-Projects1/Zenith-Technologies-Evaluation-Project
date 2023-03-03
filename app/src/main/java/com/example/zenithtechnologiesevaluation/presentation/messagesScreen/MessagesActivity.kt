package com.example.zenithtechnologiesevaluation.presentation.messagesScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.example.mvvmpractice.databinding.ActivityMessagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessagesBinding
    private lateinit var viewModel : MessagesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViewModel()

        stateManagementFun()

        getMessagesFromServer()

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this@MessagesActivity)[MessagesViewModel::class.java]
    }

    private fun getMessagesFromServer(){
        viewModel.getProductsListApiCall()
    }

    private fun stateManagementFun(){
        binding.apply {

            lifecycle.coroutineScope.launchWhenCreated {

                viewModel.state.collect{

                    when(it){

                        is MessagesViewModel.StateController.Loading ->{
                            progressBar.visibility = View.VISIBLE
                            tvErrorMessage.visibility = View.GONE
                            messagesScrollView.visibility = View.GONE
                        }

                        is MessagesViewModel.StateController.Success ->{
                            progressBar.visibility = View.GONE
                            tvErrorMessage.visibility = View.GONE
                            messagesScrollView.visibility = View.VISIBLE
                            setDataToTable(it.data)
                        }

                        is MessagesViewModel.StateController.Error ->{
                            progressBar.visibility = View.GONE
                            tvErrorMessage.visibility = View.VISIBLE
                            tvErrorMessage.text = it.message
                            messagesScrollView.visibility = View.GONE
                        }

                        else -> Unit

                    }
                }

            }
        }
    }

    private fun setDataToTable(data: MessagesResponseModel?) {
        binding.apply {


            /**Setting data to table*/
            for(singleMessage in data?.messages!!){


                val tableRow = TableRow(this@MessagesActivity)
                tableRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)


                /**Adding id to table row*/
                val textViewId = TextView(this@MessagesActivity)
                textViewId.text = singleMessage.Id.toString()
                tableRow.addView(textViewId, TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f))


                /**Adding message to table row*/
                val textViewMsg = TextView(this@MessagesActivity)
                textViewMsg.text = singleMessage.Msg.toString()
                tableRow.addView(textViewMsg, TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f))


                /**Adding whole row to messagesTable*/
                messagesTable.addView(tableRow)

            }

        }
    }
}