package com.android.countriesapp.data.countries.database

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface CountriesDao {
    @Upsert
    suspend fun upsertCountries(countries: List<CountryEntity>)
}