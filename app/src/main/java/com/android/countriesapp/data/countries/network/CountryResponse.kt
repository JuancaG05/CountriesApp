package com.android.countriesapp.data.countries.network

data class CountryResponse(
    val name: CountryNameResponse,
    val flags: CountryFlagsResponse,
    val capital: List<String> = emptyList(),
    val currencies: Map<String, CountryCurrenciesResponse> = emptyMap()
)

data class CountryNameResponse(
    val common: String
)

data class CountryFlagsResponse(
    val png: String
)

data class CountryCurrenciesResponse(
    val name: String
)
