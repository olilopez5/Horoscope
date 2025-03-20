package com.example.horoscope

import android.content.Context

class SessionManager(context: Context) {

    val sharedPref = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
// para un FAV solo is:String y putString("",)
    fun setFav(id: String, favorite: Boolean){
        val editor = sharedPref.edit()
        editor.putBoolean("${id}_favorite", favorite)
        editor.apply()
    }

    fun setHated(id: String, favorite: Boolean){
        val editor = sharedPref.edit()
        editor.putBoolean("${id}_hated", favorite)
        editor.apply()
    }
    //guardar solo un favorito

//    fun getFav(id: String) : String {
//        return sharedPref.getString("FAV","")!!
//    }


    //para mas de un favorito usar(devuelve) boolean para comprobar las 12 claves y guardarlas en session

    fun isFav(id : String): Boolean {
        //return id == getFav()
        return sharedPref.getBoolean("${id}_favorite",false)
    }

//    fun isHated(id : String): Boolean {
//        //return id == getFav()
//        return sharedPref.getBoolean("${id}_hated",false)
//    }

}