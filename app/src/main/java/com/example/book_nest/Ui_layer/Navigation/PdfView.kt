package com.example.book_nest.Ui_layer.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun PdfView(pdfUrl: String) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(pdfUrl),
        isZoomEnable = true
    )

    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    )
}
