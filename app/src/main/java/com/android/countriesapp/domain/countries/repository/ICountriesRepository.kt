package com.android.countriesapp.domain.countries.repository

import com.android.countriesapp.domain.countries.Country

interface ICountriesRepository {
    suspend fun retrieveAllCountries(): List<Country>
}
