package com.example.roomapp.database.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.database.AnswerDao
import com.example.roomapp.database.model.Entity

class AnswerRepository(private val answerDao: AnswerDao) {

    val readAllData: LiveData<List<Entity>> = answerDao.readAllData()

    suspend fun addAnswer(entity: Entity){
        answerDao.addAnswer(entity)
    }

    suspend fun deleteAllAnswers(){
        answerDao.deleteAllAnswers()
    }

}