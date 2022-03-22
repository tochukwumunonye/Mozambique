package com.tochukwu.mozambique.data.remote.dto

data class Run(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)