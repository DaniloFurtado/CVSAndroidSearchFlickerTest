package com.example.cvsandroidsearchflicker.extensions

import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.core.text.HtmlCompat
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.Locale

fun String?.fromHtml(): AnnotatedString {
    if (this.isNullOrEmpty()) return AnnotatedString("")

    val spanned: Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    return AnnotatedString(spanned.toString())
}

fun String?.extractTextFromHtml(): String {
    if (this.isNullOrEmpty()) return ""
    return Jsoup.parse(this).text()
}

fun String?.toFormattedDateToShow(): String {
    if (this.isNullOrEmpty()) return ""

    val outputDateFormat = "EEEE, MMMM dd, yyyy HH:mm:ss"
    val inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    val inputFormatter = SimpleDateFormat(inputDateFormat, Locale.getDefault())
    val date = inputFormatter.parse(this)

    val outputFormatter = SimpleDateFormat(outputDateFormat, Locale.getDefault())
    return outputFormatter.format(date!!)
}