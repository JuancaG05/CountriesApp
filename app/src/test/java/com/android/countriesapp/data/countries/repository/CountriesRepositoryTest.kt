package com.android.countriesapp.data.countries.repository

import com.android.countriesapp.data.countries.datasources.ICountriesLocalDataSource
import com.android.countriesapp.data.countries.datasources.ICountriesRemoteDataSource
import com.android.countriesapp.domain.countries.Country
import com.android.countriesapp.domain.countries.repository.ICountriesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CountriesRepositoryTest {

    private lateinit var remoteDataSource: ICountriesRemoteDataSource
    private lateinit var localDataSource: ICountriesLocalDataSource
    private lateinit var repository: ICountriesRepository

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        localDataSource = mockk(relaxed = true)
        repository = CountriesRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `retrieveAllCountries should return countries from local data source when available`() = runBlocking {
        val localCountries = listOf(Country("LocalCountry", "flag", listOf("capital"), listOf("CUR")))
        coEvery { localDataSource.getAllCountries() } returns localCountries

        val result = repository.retrieveAllCountries()

        Assert.assertEquals(localCountries, result)
        coVerify(exactly = 0) { remoteDataSource.retrieveAllCountries() }
    }

    @Test
    fun `retrieveAllCountries should fetch from remote and save to local when local is empty`() = runBlocking {
        val remoteCountries = listOf(Country("RemoteCountry", "flag", listOf("capital"), listOf("CUR")))
        coEvery { localDataSource.getAllCountries() } throws IllegalStateException("Empty database")
        coEvery { remoteDataSource.retrieveAllCountries() } returns remoteCountries

        val result = repository.retrieveAllCountries()

        Assert.assertEquals(remoteCountries, result)
        coVerify(exactly = 1) { remoteDataSource.retrieveAllCountries() }
        coVerify(exactly = 1) { localDataSource.upsertCountries(remoteCountries) }
    }
}