package com.lemillion.minke.ui.multifab

import androidx.compose.ui.graphics.vector.ImageVector

class MultiFabItem(
    val icon: ImageVector,
    val label: String,
    val onFabItemClicked: () -> Unit
)