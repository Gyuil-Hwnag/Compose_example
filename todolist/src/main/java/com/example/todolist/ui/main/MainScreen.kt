package com.example.todolist.ui.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Todo List") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.addTodo(text)
                    text = ""
                    keyboardController?.hide()
                }),
                value = text,
                onValueChange = {
                    text = it
                },
                placeholder = { Text(text = "할 일") },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = com.example.todolist.R.drawable.ic_add),
                        contentDescription = ""
                    )
                }
            )
            Divider(modifier = Modifier.padding(8.dp))
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viewModel.todos.value) { todoItem ->
                    Column {
                        TodoItem(
                            todo = todoItem,
                            onClick = { todo ->
                                viewModel.toggle(todo.uid)
                            },
                            onDelete = { todo ->
                                viewModel.delete(todo.uid)
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = todo.title+"이 삭제되었습니다.",
                                        actionLabel = "취소"
                                    )
                                    if(result == SnackbarResult.ActionPerformed) {
                                        viewModel.restoreTodo()
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider(color = Color.Black, thickness = 1.dp)
                    }
                }
            }
        }
    }
}