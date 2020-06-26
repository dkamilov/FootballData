package com.damir.android.myscore.utils.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(textRes: Int) {
    Snackbar.make(this.requireView(), getString(textRes), Snackbar.LENGTH_SHORT).show()
}