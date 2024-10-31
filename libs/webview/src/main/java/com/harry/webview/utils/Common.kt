package com.harry.webview.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class Common {
    companion object {

        // Invokes native android sharing
        fun invokeShareIntent(context: Context, message: String) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(context, shareIntent, null)
        }
    }

}