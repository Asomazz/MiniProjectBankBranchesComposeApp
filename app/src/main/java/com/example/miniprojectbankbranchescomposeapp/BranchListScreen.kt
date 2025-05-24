package com.example.miniprojectbankbranchescomposeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BranchListScreen( onCardClicked: (Branch) -> Unit = {}, navController: NavController) {
    var branches by remember { mutableStateOf(BranchRepository.branches) }
//    var favoriteId by remember { mutableStateOf<Int?>(null) }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(branches) { branch ->
            BranchCard(
                branch = branch,
                onClick = { navController.navigate("branchDetails/${branch.id}") },
                onFavoriteClick = {
                    branch.isFavorite = !branch.isFavorite
                }
            )
        }
    }
}