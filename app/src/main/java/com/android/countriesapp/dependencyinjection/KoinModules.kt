package com.android.countriesapp.dependencyinjection

import com.android.countriesapp.data.CountriesDatabase
import com.android.countriesapp.data.countries.datasources.CountriesLocalDataSource
import com.android.countriesapp.data.countries.datasources.CountriesRemoteDataSource
import com.android.countriesapp.data.countries.datasources.ICountriesLocalDataSource
import com.android.countriesapp.data.countries.datasources.ICountriesRemoteDataSource
import com.android.countriesapp.data.countries.repository.CountriesRepository
import com.android.countriesapp.domain.countries.repository.ICountriesRepository
import com.android.countriesapp.domain.countries.usecases.RetrieveCountriesUseCase
import com.android.countriesapp.presentation.countries.CountriesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::CountriesViewModel)
}

val dataModule = module {
    single { CountriesDatabase.getDatabase(androidContext()) }
    single { get<CountriesDatabase>().countriesDao() }
    factoryOf(::CountriesRemoteDataSource) bind ICountriesRemoteDataSource::class
    factoryOf(::CountriesLocalDataSource) bind ICountriesLocalDataSource::class
    factoryOf(::CountriesRepository) bind ICountriesRepository::class
}

val domainModule = module {
    factoryOf(::RetrieveCountriesUseCase)
}
