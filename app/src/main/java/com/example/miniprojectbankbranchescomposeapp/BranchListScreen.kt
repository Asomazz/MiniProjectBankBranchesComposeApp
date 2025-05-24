package com.example.miniprojectbankbranchescomposeapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BranchListScreen(
    navController: NavController
) {
    var branches by remember { mutableStateOf(BranchRepository.branches) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Padding from screen edge
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Bank Branches",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(branches) { branch ->
                BranchCard(
                    branch = branch,
                    onClick = {
                        navController.navigate("branchDetail/${branch.id}")
                    },
                    onFavoriteClick = {
                        branches = branches.map {
                            it.copy(isFavorite = it.id == branch.id)
                        }
                    }
                )
            }
        }
    }
}
