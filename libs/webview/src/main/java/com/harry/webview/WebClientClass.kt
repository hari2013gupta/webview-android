package com.harry.webview

import android.content.Context
import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat

class WebClientClass : WebViewClient() {
    /**
     * Uses:
     *
     *         // Configure asset loader with custom domain
     *         // *NOTE* :
     *         // The assets path handler is set with the sub path /views-widgets-samples/ here because we
     *         // are tyring to ensure that the address loaded with
     *         // loadUrl("https://raw.githubusercontent.com/views-widgets-samples/assets/index.html") does
     *         // not conflict with a real web address. In this case, if the path were only /assests/ we
     *         // would need to load "https://raw.githubusercontent.com/assets/index.html" in order to
     *         // access our local index.html file.
     *         // However we cannot guarantee "https://raw.githubusercontent.com/assets/index.html" is not
     *         // a valid web address. Therefore we must let the AssetLoader know to expect the
     *         // /views-widgets-samples/ sub path as well as the /assets/.
     *         val assetLoader = WebViewAssetLoader.Builder()
     *             .setDomain("raw.githubusercontent.com")
     *             .addPathHandler(
     *                 "/views-widgets-samples/assets/",
     *                 WebViewAssetLoader.AssetsPathHandler(this)
     *             )
     *             .addPathHandler(
     *                 "/views-widgets-samples/res/",
     *                 WebViewAssetLoader.ResourcesPathHandler(this)
     *             )
     *             .build()
     *
     *         // Set clients
     *         binding.webview.webViewClient = MyWebViewClient(assetLoader)
     *
     *         // Set Title
     *         title = getString(R.string.app_name)
     *
     *         // Setup debugging; See https://developers.google.com/web/tools/chrome-devtools/remote-debugging/webviews for reference
     *         if (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0) {
     *             WebView.setWebContentsDebuggingEnabled(true)
     *         }
     *
     *         // Enable Javascript
     *         binding.webview.settings.javaScriptEnabled = true
     *
     *         // Create a JS object to be injected into frames; Determines if WebMessageListener
     *         // or WebAppInterface should be used
     *         createJsObject(
     *             binding.webview,
     *             jsObjName,
     *             allowedOriginRules
     *         ) { message -> invokeShareIntent(message) }
     *
     *         // Load the content
     *         binding.webview.loadUrl("https://raw.githubusercontent.com/views-widgets-samples/assets/index.html")
     *     }
     * */
    // Creating the custom WebView Client Class
    private class CustomWebClient(private val assetLoader: WebViewAssetLoader) :
        WebViewClientCompat() {
        override fun shouldInterceptRequest(
            view: WebView,
            request: WebResourceRequest
        ): WebResourceResponse? {
            return assetLoader.shouldInterceptRequest(request.url)
        }
    }

}
