package com.xing.playandroid.ui.components

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebHistoryItem
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
        webView.settings.setGeolocationEnabled(true)
        webView.settings.setSupportZoom(false)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.mediaPlaybackRequiresUserGesture = true
        webView.webViewClient = PlayWebViewClient(onReceivedTitle)
        webView.loadUrl(url)
        webView
    }, update = {

    })
}


class PlayWebViewClient(private val onReceivedTitle: (String) -> Unit = {}) : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        view?.let {
            onReceivedTitle(it.title ?: "")
        }
    }
}
