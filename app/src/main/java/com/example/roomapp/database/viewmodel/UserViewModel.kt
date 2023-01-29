package com.example.roomapp.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.database.AnswerDatabase
import com.example.roomapp.database.repository.AnswerRepository
import com.example.roomapp.database.model.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Entity>>
    private val repository: AnswerRepository

    init {
        val userDao = AnswerDatabase.getDatabase(
            application
        ).userDao()
        repository = AnswerRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addAnswer(entity: Entity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAnswer(entity)
        }
    }


    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllAnswers()
        }
    }

}