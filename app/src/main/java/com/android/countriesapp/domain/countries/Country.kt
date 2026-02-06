package com.android.countriesapp.domain.countries

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val flagImageUrl: String,
    val capital: List<String>,
    val currencies: List<String>
)
