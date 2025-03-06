package com.example.horoscope

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    var horoscopeList : List<Horoscope> = Horoscope.horoscopeList

    lateinit var recyclerView : RecyclerView
    lateinit var adapter : HoroscopeAdapter


    override fun onResume() {
        super.onResume()
        // adapter recoge el click pero o sabe que hacer
        //recibe un solo parametro

        adapter = HoroscopeAdapter(horoscopeList){ position ->
            val horoscope = horoscopeList[position]

            //Toast.makeText(this, horoscopo.name,Toast.LENGTH_SHORT).show()
            // DetailActivity::class.java -> pasa el tipo de la clase , no el objeto

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_HOROSCOPE_ID, horoscope.id)
            startActivity(intent)


        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main,menu)

        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("SEARCH","He pulsado ENTER")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
//                Log.i("SEARCH",newText)
                // se filtra sobre la lista original
                horoscopeList = Horoscope.horoscopeList.filter {
                    getString(it.name).contains(newText,true)
                }

                adapter.updateIt(horoscopeList)
                return false
            }

        })

        return true
    }
}