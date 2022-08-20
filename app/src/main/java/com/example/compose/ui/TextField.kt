package com.example.compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldString() {
//    val textValue = remember {
//        mutableStateOf("")
//    }
    val (text, setValue) = remember {
        mutableStateOf("")
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
//            value = textValue.value,
//            onValueChange = {
//                textValue.value = it },
                value = text,
                onValueChange = { setValue },
            )
            Button(onClick = {
                keyboardController?.let {
                   it.hide()
                }
                // Snack Bar는 Coroutine Scope 내에서 실행해야 한다.
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("text : $text")
                }
            }) {
                Text(text = "클릭")
            }
        }
    }
}