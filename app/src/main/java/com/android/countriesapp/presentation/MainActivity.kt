package com.android.countriesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.countriesapp.domain.countries.Country
import com.android.countriesapp.presentation.countries.CountriesListScreen
import com.android.countriesapp.presentation.countries.CountryDetailsScreen
import com.android.countriesapp.presentation.theme.CountriesAppTheme
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesAppTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "countriesList"
    ) {
        composable(route = "countriesList") {
            CountriesListScreen(
                onClickCountryCard = { country ->
                    val countryJson = Json.encodeToString(Country.serializer(), country)
                    val encodedCountryJson = URLEncoder.encode(countryJson, StandardCharsets.UTF_8.toString())
                    navController.navigate(
                        route = "countryDetails/${encodedCountryJson}"
                    )
                }
            )
        }
        composable(
            route = "countryDetails/{countryJson}",
            arguments = listOf(navArgument("countryJson") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val encodedCountryJson = navBackStackEntry.arguments?.getString("countryJson")
            val countryJson = encodedCountryJson?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            }
            val country = countryJson?.let { Json.decodeFromString<Country>(it) }
            country?.let {
                CountryDetailsScreen(
                    country = it,
                    onClickBack = { navController.popBackStack() })
            }
        }
    }
}
