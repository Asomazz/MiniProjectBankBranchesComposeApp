package com.example.miniprojectbankbranchescomposeapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class NavRoutesEnum(val value: String) {
NAV_ROUTE_BRANCH_LIST("branchList"),
NAV_ROUTE_BRANCH_DETAILS("branchDetail");
}

@Composable
fun BranchNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutesEnum.NAV_ROUTE_BRANCH_LIST.value
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutesEnum.NAV_ROUTE_BRANCH_LIST.value) {
            BranchListScreen(
                navController = navController
            )
        }

        composable("${NavRoutesEnum.NAV_ROUTE_BRANCH_DETAILS.value}/{branchId}") { backStackEntry ->
            val branchId = backStackEntry.arguments?.getString("branchId")?.toIntOrNull()
            val branch = BranchRepository.branches.find { it.id == branchId }

            if (branch != null) {
                BranchDetailScreen(
                    branch = branch,
                    onBack = { navController.popBackStack() },
                    onFavoriteClick = {
                        // Toggle favorite (basic logic — update from repo in real apps)
                        branch.isFavorite = !branch.isFavorite
                    }
                )
            }
        }
    }
}
