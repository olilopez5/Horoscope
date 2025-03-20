package com.example.horoscope.utils

import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions


class TranslationUtils {
    // Crea el traductor para inglés a español

    companion object {
        fun getEnglishToSpanishTranslator(): Translator {
            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH) // Idioma de origen
                .setTargetLanguage(TranslateLanguage.SPANISH) // Idioma de destino
                .build()

            // Crear el traductor con las opciones configuradas
            return Translation.getClient(options)
        }
    }
}
