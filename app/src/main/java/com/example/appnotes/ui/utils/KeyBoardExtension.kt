package com.example.appnotes.ui.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT

fun View.hideKeyboard(){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken,0)
    this.clearFocus()
}
fun View.showKeyboard(){
    this.requestFocus()
    val imn= context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imn.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}