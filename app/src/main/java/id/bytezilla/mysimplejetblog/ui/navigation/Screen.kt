package id.bytezilla.mysimplejetblog.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("home/{blogId}") {
        fun createRoute(blogId: Long) = "home/$blogId"
    }
    object About : Screen("about")
}
