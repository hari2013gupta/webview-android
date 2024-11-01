package com.harry.sdk3010webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.harry.webview.WebAppInterface
import com.harry.webview.WebChromeClass
import com.harry.webview.WebClientClass

class MainActivity : AppCompatActivity() {
    private lateinit var wSettings: WebSettings
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        webView = findViewById(R.id.webview)
//        webView.loadUrl("https://green-carmelle-86.tiiny.site")
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        finish()
                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}