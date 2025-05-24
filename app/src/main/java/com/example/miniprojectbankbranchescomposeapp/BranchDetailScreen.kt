package com.example.miniprojectbankbranchescomposeapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchDetailScreen(
    branch: Branch,
    onBack: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(branch.name, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onFavoriteClick) {
                        Text(
                            text = if (branch.isFavorite) "🌟" else "⭐",
                            fontSize = 22.sp
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            branch.imageUri?.let { painterResource(id = it) }?.let {
                Image(
                    painter = it,
                    contentDescription = "Branch Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Type: ${branch.type}", fontSize = 16.sp)
            Text("Address: ${branch.address}", fontSize = 16.sp)
            Text("Phone: ${branch.phone}", fontSize = 16.sp)
            Text("Hours: ${branch.hours}", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(branch.location))
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Open Location", color = Color.White)
            }
        }
    }
}