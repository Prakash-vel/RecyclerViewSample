package com.example.recyclerviewsample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NameData::class], version = 1, exportSchema = false)
abstract class NameDatabase : RoomDatabase() {
    abstract val NameDatabaseDao: NameDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: NameDatabase? = null
        fun getInstance(context: Context): NameDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NameDatabase::class.java,
                        "name_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
