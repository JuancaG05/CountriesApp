package com.android.countriesapp.data.countries.repository

import com.android.countriesapp.data.countries.datasources.ICountriesRemoteDataSource
import com.android.countriesapp.domain.countries.Country
import com.android.countriesapp.domain.countries.repository.ICountriesRepository

class CountriesRepository(
    private val countriesRemoteDataSource: ICountriesRemoteDataSource
) : ICountriesRepository {
    override suspend fun retrieveAllCountries(): List<Country> {
        val countries = countriesRemoteDataSource.retrieveAllCountries()
        return countries
    }
}
