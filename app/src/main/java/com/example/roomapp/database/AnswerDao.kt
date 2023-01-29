package com.example.roomapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.database.model.Entity

@Dao
interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAnswer(entity: Entity)

    @Query("DELETE FROM answer_table")
    suspend fun deleteAllAnswers()

    @Query("SELECT * FROM answer_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Entity>>

}