package com.example.horoscope

import android.content.Context

class SessionManager(context: Context) {

    val sharedPref = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)

    fun setFav(id: String){
        val editor = sharedPref.edit()
        editor.putString("FAV",id)
        editor.apply()
    }

    //para mas de un favorito usar(devuelve) boolean para comprobar las 12 claves y guardarlas en session

    fun getFav() : String {
        return sharedPref.getString("FAV","")!!
    }

    fun isFav(id : String): Boolean {
        return id == getFav()
    }

}