package com.example.todolist.ui.main

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.model.Todo
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val todoRepository: TodoRepository
) : AndroidViewModel(application) {

    private val _todos = mutableStateOf(emptyList<Todo>())
    val todos: State<List<Todo>> = _todos

    private var recentlyDeleteTodo: Todo? = null

    init {
        viewModelScope.launch {
            todoRepository.observeTodos()
                .collect { todos ->
                    _todos.value = todos
                }
        }
    }

    fun addTodo(text: String) {
        viewModelScope.launch {
            val todo = Todo(title = text)
            todoRepository.addTodo(todo)
        }
//        Log.d("reponse!!", text)
    }

    fun toggle(uid: Int) {
        val todo = _todos.value.find { todo ->
            todo.uid == uid
        }
        todo?.let {
            viewModelScope.launch {
                todoRepository.updateTodo(it.copy(isDone = !it.isDone).apply {
                    this.uid = it.uid
                })
            }
        }
    }

    fun delete(uid: Int) {
        val todo = _todos.value.find { todo ->
            todo.uid == uid
        }
        todo?.let {
            viewModelScope.launch {
                todoRepository.deleteTodo(it)
                recentlyDeleteTodo = it
            }
        }
    }

    fun restoreTodo() {
        viewModelScope.launch {
            todoRepository.addTodo(recentlyDeleteTodo ?: return@launch)
            recentlyDeleteTodo = null
        }
    }
}