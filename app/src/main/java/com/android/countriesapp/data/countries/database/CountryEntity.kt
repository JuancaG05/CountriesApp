package com.android.countriesapp.data.countries.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey
    val name: String,
    val flagImageUrl: String,
    val capital: List<String>,
    val currencies: List<String>
)