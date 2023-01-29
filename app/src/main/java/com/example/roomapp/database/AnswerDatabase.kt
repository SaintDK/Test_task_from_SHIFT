package com.example.roomapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.database.model.Entity

@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class AnswerDatabase : RoomDatabase() {

    abstract fun userDao(): AnswerDao

    companion object {
        @Volatile
        private var INSTANCE: AnswerDatabase? = null

        fun getDatabase(context: Context): AnswerDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnswerDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}