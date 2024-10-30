package com.harry.sdk3010webview.webview

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.webkit.JavascriptInterface
import android.widget.Toast


/** Instantiate the interface and set the context.  */
class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page.  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun showAlert(message: String?) {
        val alert: AlertDialog = AlertDialog.Builder(mContext)
            .create()
        alert.setTitle("My Js Alert")
        alert.setMessage(message)
        alert.setButton("OK",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
    }
}