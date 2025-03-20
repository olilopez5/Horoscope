package com.example.horoscope.utils

import android.util.Log
import com.google.mlkit.nl.translate.Translator

class HoroscopeTranslator {
    // Crear el traductor de inglés a español
    private val translator: Translator = TranslationUtils.getEnglishToSpanishTranslator()

    // Traducir el texto
    fun translateHoroscope(horoscopeText: String?, callback: TranslateCallback) {
        translator.downloadModelIfNeeded().addOnSuccessListener {
            // Intentamos traducir el texto
            translator.translate(horoscopeText!!)
                .addOnSuccessListener { translatedText: String? ->
                    // Llamamos al callback con el texto traducido
                    callback.onSuccess(translatedText)
                }
                .addOnFailureListener { e: Exception ->
                    // Si hay un error en la traducción, llamamos al callback con el error
                    callback.onFailure(e.message)
                }
        }.addOnFailureListener {exception ->
            // Si ocurre un error durante la descarga del modelo
            Log.e("MLKit", "Error downloading model: ${exception.message}")
        }
    }

    // Interfaz para el callback
    interface TranslateCallback {
        fun onSuccess(translatedText: String?)

        fun onFailure(errorMessage: String?)
    }
}

