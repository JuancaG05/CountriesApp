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

    override suspend fun getAllCountries(): List<Country> {
        val countries = countriesDao.getAllCountries().map { it.toModel() }
        if (countries.isEmpty()) {
            throw IllegalStateException("Empty database")
        } else {
            return countries
        }
    }

    private fun Country.toEntity() =
        CountryEntity(
            name = name,
            flagImageUrl = flagImageUrl,
            capital = capital,
            currencies = currencies
        )

    private fun CountryEntity.toModel() =
        Country(
            name = name,
            flagImageUrl = flagImageUrl,
            capital = capital,
            currencies = currencies
        )
}