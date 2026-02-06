package com.android.countriesapp.data.countries.datasources

import com.android.countriesapp.data.WebClient
import com.android.countriesapp.data.countries.network.CountryResponse
import com.android.countriesapp.domain.countries.Country

class CountriesRemoteDataSource : ICountriesRemoteDataSource {
    override suspend fun retrieveAllCountries(): List<Country> {
        val response = WebClient.apiService.retrieveAllCountries()
        return response.map { it.toModel() }
    }

    private fun CountryResponse.toModel(): Country =
        Country(
            name = name.common,
            flagImageUrl = flags.png,
            capital = capital,
            currencies = currencies.map { it.value.name }
        )
}