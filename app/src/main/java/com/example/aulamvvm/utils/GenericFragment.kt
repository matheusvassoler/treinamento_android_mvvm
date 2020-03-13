package com.example.aulamvvm.utils

import android.app.AlertDialog
import androidx.fragment.app.Fragment

open class GenericFragment: Fragment() {

    public fun showMessage(msg: String) {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("")
        builder.setMessage(msg)
        var dialog: AlertDialog = builder.create()
        dialog.show()
    }
}