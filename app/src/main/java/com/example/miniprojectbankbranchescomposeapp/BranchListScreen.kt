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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color


@Composable
fun BranchListScreen(
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }
    var branches by remember { mutableStateOf(BranchRepository.branches) }
    var isFiltered by remember { mutableStateOf(false) }
    var isSorted by remember { mutableStateOf(false) }

    val displayedBranches = branches
        .filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                    it.address.contains(searchQuery, ignoreCase = true)
        }
        .let { list ->
            if (isFiltered) list.filter { it.hours.contains("9") } else list
        }
        .let { list ->
            if (isSorted) list.sortedBy { it.name } else list
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "🏦 Bank Branches",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search by name or address") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { isFiltered = !isFiltered },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Filter by Hours")
            }

            Button(
                onClick = { isSorted = !isSorted },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Sort A-Z")
            }

        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(displayedBranches) { branch ->
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
