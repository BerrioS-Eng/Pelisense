package com.project.pelisense.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.pelisense.data.SampleData
import com.project.pelisense.ui.components.CyberTab
import com.project.pelisense.ui.screens.HomeScreen
import com.project.pelisense.ui.screens.MovieDetailsScreen
import com.project.pelisense.ui.screens.MyListScreen
import com.project.pelisense.ui.screens.ProfileScreen
import com.project.pelisense.ui.screens.QuestionnaireScreen
import com.project.pelisense.ui.screens.SplashScreen
import com.project.pelisense.ui.screens.WelcomeScreen

object PelisenseRoutes {
    const val SPLASH = "splash"
    const val WELCOME = "welcome"
    const val QUESTIONNAIRE = "questionnaire"
    const val HOME = "home"
    const val DETAILS = "details/{movieId}"
    const val PROFILE = "profile"
    const val MY_LIST = "my_list"

    fun detailsRoute(movieId: String) = "details/$movieId"
}

@Composable
fun PelisenseNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = PelisenseRoutes.SPLASH
    ) {
        // SPLASH SCREEN
        composable(PelisenseRoutes.SPLASH) {
            SplashScreen(
                onNavigateNext = {
                    navController.navigate(PelisenseRoutes.WELCOME) {
                        popUpTo(PelisenseRoutes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        // WELCOME / LOGIN SCREEN
        composable(PelisenseRoutes.WELCOME) {
            WelcomeScreen(
                onContinueClick = {
                    navController.navigate(PelisenseRoutes.QUESTIONNAIRE)
                }
            )
        }

        // QUESTIONNAIRE SCREEN
        composable(PelisenseRoutes.QUESTIONNAIRE) {
            QuestionnaireScreen(
                onGenerateClick = {
                    navController.navigate(PelisenseRoutes.HOME) {
                        popUpTo(PelisenseRoutes.WELCOME) { inclusive = false }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // HOME / CATALOG SCREEN
        composable(PelisenseRoutes.HOME) {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(PelisenseRoutes.detailsRoute(movie.id))
                },
                onProfileClick = {
                    navController.navigate(PelisenseRoutes.PROFILE)
                },
                onTabSelected = { tab ->
                    when (tab) {
                        CyberTab.MOOD -> navController.navigate(PelisenseRoutes.QUESTIONNAIRE)
                        CyberTab.EXPLORE -> { /* Already home */ }
                        CyberTab.HISTORY -> navController.navigate(PelisenseRoutes.MY_LIST)
                        CyberTab.PROFILE -> navController.navigate(PelisenseRoutes.PROFILE)
                    }
                }
            )
        }

        // MOVIE DETAILS SCREEN
        composable(
            route = PelisenseRoutes.DETAILS,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            val movie = SampleData.sampleMovies.find { it.id == movieId } ?: SampleData.heroMovie

            MovieDetailsScreen(
                movie = movie,
                onBackClick = { navController.popBackStack() },
                onProfileClick = { navController.navigate(PelisenseRoutes.PROFILE) }
            )
        }

        // PROFILE SCREEN
        composable(PelisenseRoutes.PROFILE) {
            ProfileScreen(
                onTabSelected = { tab ->
                    when (tab) {
                        CyberTab.MOOD -> navController.navigate(PelisenseRoutes.QUESTIONNAIRE)
                        CyberTab.EXPLORE -> navController.navigate(PelisenseRoutes.HOME)
                        CyberTab.HISTORY -> navController.navigate(PelisenseRoutes.MY_LIST)
                        CyberTab.PROFILE -> { /* Already profile */ }
                    }
                },
                onSignOutClick = {
                    navController.navigate(PelisenseRoutes.WELCOME) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // MY LIST SCREEN
        composable(PelisenseRoutes.MY_LIST) {
            MyListScreen(
                onMovieClick = { movie ->
                    navController.navigate(PelisenseRoutes.detailsRoute(movie.id))
                },
                onTabSelected = { tab ->
                    when (tab) {
                        CyberTab.MOOD -> navController.navigate(PelisenseRoutes.QUESTIONNAIRE)
                        CyberTab.EXPLORE -> navController.navigate(PelisenseRoutes.HOME)
                        CyberTab.HISTORY -> { /* Already my list */ }
                        CyberTab.PROFILE -> navController.navigate(PelisenseRoutes.PROFILE)
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
