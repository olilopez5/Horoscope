package com.example.horoscope

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

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

    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.recycledView)
    }
}