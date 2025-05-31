package com.nutrisport.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.nutrisport.auth.component.GoogleButton
import com.nutrisport.shared.Alpha
import com.nutrisport.shared.BebasNeueFont
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.Surface
import com.nutrisport.shared.TextSecondary
import rememberMessageBarState

@Composable
fun AuthScreen() {
    val messageBarState = rememberMessageBarState()
    var loadingState by remember { mutableStateOf(false) }

    Scaffold { padding ->
        // scaffold의 padding은 systembar와 navigationbar의 패딩을 고려한다
        ContentWithMessageBar(
            contentBackgroundColor = Surface,
            modifier = Modifier.padding(
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding()
            ),
            messageBarState = messageBarState,
            errorMaxLines = 2
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(all = 20.dp)) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { // weight를 1f로 주면 남는 공간을 다 채워준다
                    Text(
                        text = "NUTRISPORT",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )
                    Text(
                        text = "Sign in to continue",
                        modifier = Modifier.fillMaxWidth().alpha(Alpha.HALF),
                        textAlign = TextAlign.Center, // ROBOTO가 기본 fontFamily
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextSecondary
                    )
                }
                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = { res ->
                        // success or failure msg
                        res
                            .onSuccess { user ->
                                println(user)
                                messageBarState.addSuccess("Login Success")
                                loadingState = false
                            }
                            .onFailure { e ->
                                if (e.message?.contains("network error") == true){
                                    messageBarState.addError("Network Error")
                                }else if(e.message?.contains("idToken is null") == true){
                                    messageBarState.addError("Invalid Token")
                                }else{
                                    messageBarState.addError(e.message ?: "Unknown Error")
                                }
                                loadingState = false
                            }
                    }
                ) {
                    GoogleButton(
                        loading = loadingState,
                        onClick = {
                            loadingState = true
                            this@GoogleButtonUiContainerFirebase.onClick()
                        })
                }
//                GoogleButton(
//                    loading = loadingState,
//                    onClick = {
//
//                })
            }
        }

    }
}