package com.android.countriesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.countriesapp.presentation.countries.CountriesListScreen
import com.android.countriesapp.presentation.theme.CountriesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesAppTheme {
                CountriesListScreen(
                    onClickCountryCard = {}
                )
            }
        }
    }
}