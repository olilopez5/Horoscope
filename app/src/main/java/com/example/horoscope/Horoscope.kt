package com.example.horoscope

class Horoscope (
    val id : String,
    val icon : Int,
    val name : Int,
    val dates : Int,
    ) {

    companion object {

        val horoscopeList = listOf(
            Horoscope(id = "aries", R.drawable.aries_icon, R.string.horoscope_name_aries,R.string.horoscope_date_aries),
            Horoscope(id = "taurus", R.drawable.taurus_icon, R.string.horoscope_name_taurus, R.string.horoscope_date_taurus),
            Horoscope(id = "gemini", R.drawable.gemini_icon, R.string.horoscope_name_gemini, R.string.horoscope_date_gemini),
            Horoscope(id = "cancer", R.drawable.cancer_icon, R.string.horoscope_name_cancer, R.string.horoscope_date_cancer),
            Horoscope(id = "leo", R.drawable.leo_icon, R.string.horoscope_name_leo, R.string.horoscope_date_leo),
            Horoscope(id = "virgo", R.drawable.virgo_icon, R.string.horoscope_name_virgo, R.string.horoscope_date_virgo),
            Horoscope(id = "libra", R.drawable.libra_icon, R.string.horoscope_name_libra, R.string.horoscope_date_libra),
            Horoscope(id = "scorpio", R.drawable.scorpio_icon, R.string.horoscope_name_scorpio, R.string.horoscope_date_scorpio),
            Horoscope(id = "sagittarius", R.drawable.sagittarius_icon, R.string.horoscope_name_sagittarius, R.string.horoscope_date_sagittarius),
            Horoscope(id = "capricorn", R.drawable.capricorn_icon, R.string.horoscope_name_capricorn, R.string.horoscope_date_capricorn),
            Horoscope(id = "aquarius", R.drawable.aquarius_icon, R.string.horoscope_name_aquarius, R.string.horoscope_date_aquarius),
            Horoscope(id = "pisces", R.drawable.pisces_icon, R.string.horoscope_name_pisces, R.string.horoscope_date_pisces),
            )

        fun findById (id : String): Horoscope{
            //comprueba que el id que le hemos pasado es es mismo del predicado it
            return horoscopeList.first { it.id == id }
        }
    }

}