package com.example.jetcomposelearning.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun getDeviceWidth() {
    val displayMetrics = LocalConfiguration.current
    val width = displayMetrics.screenWidthDp.dp
}


