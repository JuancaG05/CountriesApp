package com.android.countriesapp.data.countries.datasources

import com.android.countriesapp.data.countries.database.CountriesDao
import com.android.countriesapp.data.countries.database.CountryEntity
import com.android.countriesapp.domain.countries.Country

class CountriesLocalDataSource(
    private val countriesDao: CountriesDao
) : ICountriesLocalDataSource {
    override suspend fun upsertCountries(countries: List<Country>) {
        countriesDao.upsertCountries(countries.map { it.toEntity() })
    }

    private fun Country.toEntity() =
        CountryEntity(
            name = name,
            flagImageUrl = flagImageUrl,
            capital = capital,
            currencies = currencies
        )
}