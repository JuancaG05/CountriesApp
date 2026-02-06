package com.android.countriesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.countriesapp.data.countries.database.CountriesDao
import com.android.countriesapp.data.countries.database.CountryEntity

@Database(
    entities = [CountryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CountriesDatabase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao

    companion object {
        @Volatile
        private var INSTANCE: CountriesDatabase? = null

        fun getDatabase(context: Context): CountriesDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    CountriesDatabase::class.java,
                    "countries_database"
                ).build()
                    .also { INSTANCE = it }
            }
    }
}