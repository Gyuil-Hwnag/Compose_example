package com.example.todolist.ui.main

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.model.Todo
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val todoRepository: TodoRepository
) : AndroidViewModel(application) {

    private val _todos = mutableStateOf(emptyList<Todo>())
    val todos: State<List<Todo>> = _todos

    private var recentlyDeleteTodo: Todo? = null

    // 1. CoroutineScope와 viewModelScope의 차이점 찾아보려고 작성
//    private val jobTodo = CoroutineScope(Dispatchers.IO).launch {
//        todoRepository.observeTodos().collect { todos ->
//            _todos.value = todos
//        }
//    }

    init {
        viewModelScope.launch {
            todoRepository.observeTodos().collect { todos ->
                    _todos.value = todos
            }
        }

        // 2. CoroutineScope와 viewModelScope의 차이점 찾아보려고 작성
//        jobTodo.start()
    }

    // 3. CoroutineScope와 viewModelScope의 차이점 찾아보려고 작성
//    override fun onCleared() {
//        super.onCleared()
//        jobTodo.cancel()
//    }

    private val _getTodos = mutableStateOf(emptyList<Todo>())
    val gettodos: State<List<Todo>> = _getTodos

    fun getTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            _getTodos.value = todoRepository.getTodos()
        }
    }

    fun addTodo(text: String) {
        viewModelScope.launch {
            val todo = Todo(title = text)
            todoRepository.addTodo(todo)
        }
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