package com.harry.sdk3010webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.harry.sdk3010webview.webview.WebAppInterface
import com.harry.sdk3010webview.webview.WebChromeClass
import com.harry.sdk3010webview.webview.WebClientClass


class MainActivity : AppCompatActivity() {
    private lateinit var wSettings: WebSettings

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val webView: WebView = findViewById(R.id.webview)
//        webView.loadUrl("https://www.google.com")
        webView.isClickable = true

        wSettings = webView.settings
        wSettings.javaScriptEnabled = true

        webView.webViewClient = WebClientClass()
        webView.webChromeClient = WebChromeClass()

        /**
         * Load Our Custom JS Inside WebView
         */
        webView.loadUrl("file:///android_asset/web/index.html");
        webView.addJavascriptInterface(WebAppInterface(this), "Android")
    }
}