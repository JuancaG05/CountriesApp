package com.android.countriesapp.data.countries.datasources

import com.android.countriesapp.domain.countries.Country

interface ICountriesLocalDataSource {
    suspend fun upsertCountries(countries: List<Country>)
    suspend fun getAllCountries(): List<Country>
}
