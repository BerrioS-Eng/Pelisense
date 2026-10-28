package com.project.pelisense.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.pelisense.domain.data.SampleData
import com.project.pelisense.ui.components.CyberTab
import com.project.pelisense.ui.screens.HomeScreen
import com.project.pelisense.ui.screens.MovieDetailsScreen
import com.project.pelisense.ui.screens.MyListScreen
import com.project.pelisense.ui.screens.ProfileScreen
import com.project.pelisense.ui.screens.QuestionnaireScreen
import com.project.pelisense.ui.screens.SplashScreen
import com.project.pelisense.ui.screens.WelcomeScreen
import com.project.pelisense.ui.view_models.ProfileViewModel
import com.project.pelisense.ui.view_models.SignInViewModel


@Composable
fun PelisenseNavHost(
    navController: NavHostController = rememberNavController(),
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
            val viewModel = hiltViewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val navigateToQuestionnaire = {
                navController.navigate(PelisenseRoutes.QUESTIONNAIRE) {
                    popUpTo(PelisenseRoutes.WELCOME) {
                        inclusive = true
                    }
                }
            }

            WelcomeScreen(state = state, onContinueClick = {
                viewModel.signIn { navigateToQuestionnaire() }
            })
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
                        CyberTab.EXPLORE -> { /* Already home */
                        }

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
            val viewModel: ProfileViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            ProfileScreen(
                onTabSelected = { tab ->
                    when (tab) {
                        CyberTab.MOOD -> navController.navigate(PelisenseRoutes.QUESTIONNAIRE)
                        CyberTab.EXPLORE -> navController.navigate(PelisenseRoutes.HOME)
                        CyberTab.HISTORY -> navController.navigate(PelisenseRoutes.MY_LIST)
                        CyberTab.PROFILE -> { /* Already profile */
                        }
                    }
                },
                onSignOutClick = {
                    viewModel.singOut()
                    navController.navigate(PelisenseRoutes.WELCOME) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                state = state
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
                        CyberTab.HISTORY -> { /* Already my list */
                        }

                        CyberTab.PROFILE -> navController.navigate(PelisenseRoutes.PROFILE)
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
