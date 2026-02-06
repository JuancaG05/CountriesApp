package com.android.countriesapp.domain.countries.usecases

import com.android.countriesapp.domain.countries.repository.ICountriesRepository

class RetrieveCountriesUseCase(
    private val countriesRepository: ICountriesRepository
) {
    suspend fun run() =
        countriesRepository.retrieveAllCountries()
}
