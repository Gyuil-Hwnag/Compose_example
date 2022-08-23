package com.example.sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample1.ui.HomeScreen
import com.example.sample1.ui.ResultScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            val navController = rememberNavController()

            val bmi = viewModel.data.value

            NavHost(navController = navController, startDestination = "Home") {
                composable(route = "Home") {
                    HomeScreen { weight, height ->
                        viewModel.setData(weight, height)
                        navController.navigate("Result")
                    }
                }
                composable(route = "Result") {
                    ResultScreen(bmi = bmi) {
                        navController.popBackStack()
                    }
                }
            }

        }
    }
}

