package com.example.compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun NavigationControl() {
    val (value, setValue) = remember {
        mutableStateOf("")
    }

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "First"
    ) {
        composable("First") { FirtScreen(
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
fun FirtScreen(
    toSecond: () -> Unit,
    toThird: (textValue: String) -> Unit) {
    val (value, setValue) = remember {
        mutableStateOf("")
    }
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
        TextField(value = value, onValueChange = setValue)
        Button(onClick = {
            toThird(value)
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