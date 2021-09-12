package com.xing.xueandroid.ui.components

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(modifier: Modifier = Modifier, url: String) {
    AndroidView(factory = { context ->
        val webView = android.webkit.WebView(context)
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.mediaPlaybackRequiresUserGesture = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
        webView
    })
}