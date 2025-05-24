package com.example.miniprojectbankbranchescomposeapp

data class Branch(
    val id: Int,
    val name: String,
    val type: BranchType,
    val address: String,
    val phone: String,
    val hours: String,
    val location: String,
    val imageUri: Int?,
    var isFavorite: Boolean = false
)

enum class BranchType {
    ATM, MAIN
}