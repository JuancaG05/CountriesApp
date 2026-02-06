package com.android.countriesapp.data.countries.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CountriesDao {
    @Upsert
    suspend fun upsertCountries(countries: List<CountryEntity>)

    @Query("SELECT * FROM countries")
    suspend fun getAllCountries(): List<CountryEntity>
}