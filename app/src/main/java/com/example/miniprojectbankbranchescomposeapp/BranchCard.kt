package com.example.miniprojectbankbranchescomposeapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BranchCard(
    branch: Branch,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (branch.isFavorite) Color(0xFFFCE588) else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(branch.name, style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = onFavoriteClick) {
                    Text(
                        text = if (branch.isFavorite) "🌟" else "⭐",
                        fontSize = 20.sp
                    )
                }
            }
            Text(branch.address)
        }
    }
}
