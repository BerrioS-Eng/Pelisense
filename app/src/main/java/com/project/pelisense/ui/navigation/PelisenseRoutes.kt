package com.project.pelisense.ui.navigation

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