package com.android.countriesapp.domain.countries

data class Country(
    val name: String,
    val flagImageUrl: String,
    val capital: String,
    val currencies: List<String>
)
