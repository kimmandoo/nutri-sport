package com.kimmandoo.nutrisport

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.nutrisport.navigation.SetupNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    // 여기가 진짜 composefile을 작성할 곳
    MaterialTheme {
        SetupNavGraph()
    }
}