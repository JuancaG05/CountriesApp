package com.android.countriesapp.presentation.countries

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.android.countriesapp.R
import com.android.countriesapp.domain.countries.Country
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountriesListScreen(
    onClickCountryCard: (Country) -> Unit,
    viewModel: CountriesViewModel = koinViewModel()
) {
    val countries by viewModel.countries.collectAsState()

    if (countries == null) {
        Column {
            Text(
                text = stringResource(R.string.no_internet_available)
            )
            Button(
                onClick = { viewModel.retrieveCountries() }
            ) {
                Text(
                    text = stringResource(R.string.retry)
                )
            }
        }
    } else if (countries!!.isEmpty()) {
        CircularProgressIndicator()
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(countries!!) { country ->
                CountryCard(
                    country = country,
                    onClick = { onClickCountryCard(country) }
                )
            }
        }
    }
}

@Composable
fun CountryCard(
    country: Country,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberAsyncImagePainter(
                model = country.flagImageUrl,
                placeholder = painterResource(R.drawable.unknown_flag),
                error = painterResource(R.drawable.unknown_flag)
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.width(100.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = country.name
                )
            }
        }
    }
}
