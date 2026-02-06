package com.android.countriesapp.dependencyinjection

import com.android.countriesapp.data.countries.datasources.CountriesRemoteDataSource
import com.android.countriesapp.data.countries.datasources.ICountriesRemoteDataSource
import com.android.countriesapp.data.countries.repository.CountriesRepository
import com.android.countriesapp.domain.countries.repository.ICountriesRepository
import com.android.countriesapp.domain.countries.usecases.RetrieveCountriesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {

}

val dataModule = module {
    factoryOf(::CountriesRemoteDataSource) bind ICountriesRemoteDataSource::class
    factoryOf(::CountriesRepository) bind ICountriesRepository::class
}

val domainModule = module {
    factoryOf(::RetrieveCountriesUseCase)
}
