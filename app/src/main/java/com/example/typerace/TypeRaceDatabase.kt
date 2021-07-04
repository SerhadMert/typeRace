package com.example.typerace

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.typerace.Entitys.Word
import kotlinx.coroutines.CoroutineScope


@Database(entities = arrayOf(Word::class), version = 1)
abstract class TypeRaceDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TypeRaceDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TypeRaceDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TypeRaceDatabase::class.java,
                    "typerace_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}