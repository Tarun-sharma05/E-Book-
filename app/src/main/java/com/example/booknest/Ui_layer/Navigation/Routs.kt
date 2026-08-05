package com.example.booknest.Ui_layer.Navigation

import kotlinx.serialization.Serializable
@Serializable
sealed class Routs{
    @Serializable
    data class BookByCategory(
        val category: String
    )

    @Serializable
    data class pdfView(
        val pdfUrl: String
    )

    @Serializable
    object HomeScreen

    @Serializable
    object Books


}