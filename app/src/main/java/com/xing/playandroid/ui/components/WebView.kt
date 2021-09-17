package com.xing.playandroid.ui.components

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(modifier: Modifier = Modifier, url: String, onReceivedTitle: (String) -> Unit = {}) {
    AndroidView(factory = { context ->
        val webView = WebView(context)
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.mediaPlaybackRequiresUserGesture = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = PlayWebChromeClient(onReceivedTitle)
        webView.loadUrl(url)
        webView
    }, update = {

    })
}


class PlayWebChromeClient(private val onReceivedTitle: (String) -> Unit = {}) : WebChromeClient() {

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        title?.let {
            onReceivedTitle(it)
        }
    }
}