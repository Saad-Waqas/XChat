package com.example.xchat.activity.extention

import java.util.Locale
import kotlin.text.ifEmpty

data class LanguageItem(
    val flagResId: Int,
    val code: String,
) {
    val languageName: String
        get() = name(code)

    val languageBracket: String
        get() = "${nameBase(code)}"

    companion object {
        fun name(code: String): String {
            return Locale.forLanguageTag(code).displayLanguage.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
        }

        fun nameBase(code: String): String {
            val default =
                Locale.forLanguageTag(code).getDisplayLanguage(Locale.forLanguageTag(code))
            return if (code.contains("-")) {
                val country =
                    Locale.forLanguageTag(code).getDisplayCountry(Locale.forLanguageTag(code))
                country.ifEmpty { default }
            } else default
        }
    }
}