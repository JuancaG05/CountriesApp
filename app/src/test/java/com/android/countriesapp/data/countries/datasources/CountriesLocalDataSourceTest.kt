
package com.android.countriesapp.data.countries.datasources

import com.android.countriesapp.data.countries.database.CountriesDao
import com.android.countriesapp.data.countries.database.CountryEntity
import com.android.countriesapp.domain.countries.Country
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CountriesLocalDataSourceTest {

    private val countriesDao: CountriesDao = mockk(relaxed = true)
    private val dataSource: ICountriesLocalDataSource = CountriesLocalDataSource(countriesDao)

    @Test
    fun `upsertCountries should call dao with mapped entities`() = runBlocking {
        val countries = listOf(
            Country("USA", "url", listOf("Washington"), listOf("USD")),
            Country("Canada", "url2", listOf("Ottawa"), listOf("CAD"))
        )
        val entities = countries.map {
            CountryEntity(
                name = it.name,
                flagImageUrl = it.flagImageUrl,
                capital = it.capital,
                currencies = it.currencies
            )
        }

        dataSource.upsertCountries(countries)

        coVerify { countriesDao.upsertCountries(entities) }
    }

    @Test
    fun `getAllCountries should return countries when database is not empty`() = runBlocking {
        val entities = listOf(
            CountryEntity("USA", "url", listOf("Washington"), listOf("USD")),
            CountryEntity("Canada", "url2", listOf("Ottawa"), listOf("CAD"))
        )
        val expectedCountries = entities.map {
            Country(
                name = it.name,
                flagImageUrl = it.flagImageUrl,
                capital = it.capital,
                currencies = it.currencies
            )
        }
        coEvery { countriesDao.getAllCountries() } returns entities

        val result = dataSource.getAllCountries()

        assertEquals(expectedCountries, result)
    }

    @Test(expected = IllegalStateException::class)
    fun `getAllCountries should throw exception when database is empty`() {
        runBlocking {
            coEvery { countriesDao.getAllCountries() } returns emptyList()

            dataSource.getAllCountries()
        }
    }
}
