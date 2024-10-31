package com.harry.webview

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast

/** Instantiate the interface and set the context.  */
class WebAppInterface(private val mContext: Context) {
    private val tag = WebAppInterface::class.simpleName
    /** Show a toast from the web page.  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Log.i(tag, "showToast: $toast")
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun showAlert(message: String?) {
        Log.i(tag, "showAlert: $message")
        val alert: AlertDialog = AlertDialog.Builder(mContext)
            .create()
        alert.setTitle("My Js Alert")
        alert.setMessage(message)
        alert.setButton("OK",
            DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
        alert.show()
    }
}