package id.bytezilla.mysimplejetblog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.bytezilla.mysimplejetblog.data.BlogRepository
import id.bytezilla.mysimplejetblog.ui.BlogViewModel
import id.bytezilla.mysimplejetblog.ui.ViewModelFactory
import id.bytezilla.mysimplejetblog.ui.navigation.Screen
import id.bytezilla.mysimplejetblog.ui.screen.AboutScreen
import id.bytezilla.mysimplejetblog.ui.screen.DetailScreen
import id.bytezilla.mysimplejetblog.ui.screen.HomeScreen

@Composable
fun BlogApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val repository = BlogRepository.getInstance()
    val viewModel: BlogViewModel = viewModel(factory = ViewModelFactory(repository))

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                navigateToDetail = { blogId ->
                    navController.navigate(Screen.Detail.createRoute(blogId))
                },
                navigateToAbout = {
                    navController.navigate(Screen.About.route)
                }
            )
        }
        
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("blogId") { type = NavType.LongType })
        ) { backStackEntry ->
            val blogId = backStackEntry.arguments?.getLong("blogId")
            val blog = blogId?.let { viewModel.getBlogById(it) }
            DetailScreen(
                blog = blog,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        
        composable(Screen.About.route) {
            AboutScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
