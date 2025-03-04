package com.example.horoscope

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCPE_ID"
    }

    lateinit var nameAcDet: TextView
    lateinit var dateAcDet: TextView
    lateinit var iconAcDet: ImageView

    lateinit var horoscope: Horoscope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!  // !! para no tratar los nulos
        horoscope = Horoscope.findById(id)


       initView()

       loadData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail,menu)
        return true
    }


        override fun onContextItemSelected(item: MenuItem): Boolean {

            return when (item.itemId) {
                R.id.menu_favorite -> {
                    Log.i("MENU","FAVORITOS")
                    true
                }
                R.id.menu_share -> {
                    Log.i("MENU","COMPARTIR")
                    true
                }
                else -> super.onContextItemSelected(item)
            }

    }



    private fun loadData() {

        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.dates)

        // accede a los datos para cargarlos
        nameAcDet.text = getString(horoscope.name)
        dateAcDet.text = getString(horoscope.dates)
        iconAcDet.setImageResource(horoscope.icon)
    }

    private fun initView() {

        // inicializa los datod cargados por id
        nameAcDet = findViewById(R.id.nameAcDet)
        dateAcDet = findViewById(R.id.dateAcDet)
        iconAcDet = findViewById(R.id.iconAcDet)

//        findViewById<TextView>(R.id.nameAcDet).text = "${getString(horoscope.name)}"
//        findViewById<TextView>(R.id.dateAcDet).text = "${getString(horoscope.dates)}"
//        findViewById<ImageView>(R.id.iconAcDet).setImageResource(horoscope.icon)
    }
}