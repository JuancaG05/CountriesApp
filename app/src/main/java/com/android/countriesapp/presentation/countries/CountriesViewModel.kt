package com.android.countriesapp.presentation.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.countriesapp.domain.countries.Country
import com.android.countriesapp.domain.countries.usecases.RetrieveCountriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val retrieveCountriesUseCase: RetrieveCountriesUseCase
) : ViewModel() {
    private val _countries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    init {
        retrieveCountries()
    }

    fun retrieveCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val countries = retrieveCountriesUseCase.run()
            _countries.update { countries }
        }
    }
}
