package com.example.weatherapp.utils

import android.app.Activity
import android.app.AlertDialog

fun Activity.showDialog(title: String, message: String, onClick: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ -> onClick()}
        .create()
        .show()
}

