package com.android.countriesapp.data.countries.network

import retrofit2.http.GET

interface CountriesApiService {
    @GET("all?fields=name,flags,capital,currencies")
    suspend fun retrieveAllCountries(): List<CountryResponse>
}
