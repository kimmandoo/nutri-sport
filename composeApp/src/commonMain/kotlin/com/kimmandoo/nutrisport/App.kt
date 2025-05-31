package com.kimmandoo.nutrisport

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.nutrisport.navigation.SetupNavGraph
import com.nutrisport.shared.Constants
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    // 여기가 진짜 composefile을 작성할 곳
    var appReady by remember { mutableStateOf(false) }
    MaterialTheme {
        LaunchedEffect(Unit){
            GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = Constants.WEB_CLIENT_ID))
            appReady = true
        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(), // 이상하게 날아오는 거 싫으면 이렇게
            visible = appReady
        ){
            SetupNavGraph()
        }
    }
}