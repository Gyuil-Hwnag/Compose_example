package com.example.webbrowser.ui

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.webbrowser.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun WebView(
    viewModel: MainViewModel,
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()
    val webView = rememberWebView()

    // Compose 내부에서 Coroutine 사용시에 한번만 이벤트 발생하도록
    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.undoSharedFlow.collectLatest {
                if(webView.canGoBack()) {
                    webView.goBack()
                }
                else {
                    scaffoldState.snackbarHostState.showSnackbar("마지막 페이지")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.redoSharedFlow.collectLatest {
                if(webView.canGoForward()) {
                    webView.goForward()
                }
                else {
                    scaffoldState.snackbarHostState.showSnackbar("마지막 페이지")
                }
            }
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
//            WebView(it).apply {
//                settings.javaScriptEnabled = true
//                webViewClient = WebViewClient()
//                loadUrl("https://google.com")
//            }
            webView
        },
        update = { webView ->
            webView.loadUrl(viewModel.url.value)

//            scope.launch {
//                viewModel.undoSharedFlow.collectLatest {
//                    if(webView.canGoBack()) {
//                        webView.goBack()
//                    }
//                    else {
//                        scaffoldState.snackbarHostState.showSnackbar("마지막 페이지")
//                    }
//                }
//            }
//
//            scope.launch {
//                viewModel.redoSharedFlow.collectLatest {
//                    if(webView.canGoForward()) {
//                        webView.goForward()
//                    }
//                    else {
//                        scaffoldState.snackbarHostState.showSnackbar("마지막 페이지")
//                    }
//                }
//            }
        }
    )
}

@Composable
fun rememberWebView(): WebView {
    val context = LocalContext.current

    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl("https://google.com")
        }
    }
    return webView
}