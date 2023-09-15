package com.mahdicen.knowmovies.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1 , exportSchema = false , entities = [Movie::class , Recent::class])
abstract class MyDatabase() :RoomDatabase() {

    abstract val movieDao :MovieDao
    abstract val recentDao :RecentDao

    companion object {

        @Volatile
        private var database :MyDatabase? = null

        fun getDatabase(context: Context) :MyDatabase {

            synchronized(this) {

                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext ,
                        MyDatabase::class.java ,
                        "myDatabase.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return database!!
            }

        }

    }

}