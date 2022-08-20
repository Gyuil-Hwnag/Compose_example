package com.example.compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.MainViewModel


@Composable
fun NavigationControl() {
    // value1 & value2 & value 차이 알아두기
    val value1: MutableState<String> = remember {
        mutableStateOf("")
    }
    var value2: String by remember {
        mutableStateOf("")
    }

    val (value: String, setValue: (String) -> Unit) = remember {
        mutableStateOf("")
    }

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "First"
    ) {
        composable("First") { FirstScreen(
            toSecond = { navController.navigate("Second") },
            toThird = {
                setValue(it)
                navController.navigate("Third")
            }
        ) }

        composable("Second") { SecondScreen(
            toBack = { navController.popBackStack() }
        ) }

        composable("Third") {
            ThirdScreen(
                text = value,
                toBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun FirstScreen(
    toSecond: () -> Unit,
    toThird: (textValue: String) -> Unit) {
    val viewModel = viewModel<MainViewModel>()

    // 기존의 LiveData 사용시 (Compose는 State를 기준으로 행동함)
    val value = viewModel.liveData.observeAsState("")

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "First Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            toSecond()
        }) {
            Text(text = "Second Screen")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = viewModel.data.value,
            onValueChange = { text ->
                viewModel.changeData(text)
            })
        Button(onClick = {
            toThird(viewModel.data.value)
        }) {
            Text(text = "Third Screen")
        }
    }
}

@Composable
fun SecondScreen(
    toBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Second Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            toBack()
        }) {
            Text(text = "Back Button")
        }
    }
}

@Composable
fun ThirdScreen(
    text: String,
    toBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Third Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = text)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            toBack()
        }) {
            Text(text = "Back Button")
        }
    }
}