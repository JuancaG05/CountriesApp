package com.android.countriesapp.data.countries.datasources

import com.android.countriesapp.domain.countries.Country

interface ICountriesRemoteDataSource {
    suspend fun retrieveAllCountries(): List<Country>
}