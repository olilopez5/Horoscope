package com.example.horoscope

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCPE_ID"
    }

    lateinit var nameAcDet: TextView
    lateinit var dateAcDet: TextView
    lateinit var iconAcDet: ImageView

    lateinit var horoscope: Horoscope
    var isHated = false
    var isFav = false
    private lateinit var favoriteMenu: MenuItem

    private lateinit var session: SessionManager
    lateinit var horoscopeLuckTextView: TextView
    lateinit var bottomNavigationMenu: BottomNavigationView
    lateinit var linearProgress: LinearProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        session = SessionManager(this)

        val id = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!  // !! para no tratar los nulos
        horoscope = Horoscope.findById(id)

        //guardar el favorito en la sesion
        isFav = session.isFav(id)
        isHated = session.isHated(id)

       initView()

       loadData()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail,menu)

        favoriteMenu= menu.findItem(R.id.menu_favorite)
        setFavIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            return when (item.itemId) {
                R.id.menu_favorite -> {
                    //Log.i("MENU","FAVORITOS")
                    //cambiar al estado contrario, niega un bool
                    isFav = !isFav
                    isHated = !isHated
                    //para varios favoritps
                    session.setFav(horoscope.id,isFav)
                    session.setHated(horoscope.id,isHated)

//                    un favorto
//                    if(isFav){
//                        session.setFav(horoscope.id)
//                    }else{
//                        session.setFav("")
//                    }
                    //resultado , icono fav seleccionado o no


                    setFavIcon()
                    //setHatedIcon()
                    true
                }

                R.id.menu_share -> {
                    //Log.i("MENU","COMPARTIR")
                    val sendIntent = Intent()
                    sendIntent.setAction(Intent.ACTION_SEND)
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Your luck is :")
                    //MIME TYPE, el tipo del contenido y su extension
                    sendIntent.setType("text/plain")

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                    true
                }

                //captura el click y finish cierra el activity
                android.R.id.home -> {
                    finish()

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

        isFav = session.isFav(horoscope.id)

        bottomNavigationMenu
        linearProgress


        getHoroscopeLuck (period = daily)
    }

    private fun initView() {

//habilitar boton volver por defecto

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // inicializa los datod cargados por id
        nameAcDet = findViewById(R.id.nameAcDet)
        dateAcDet = findViewById(R.id.dateAcDet)
        iconAcDet = findViewById(R.id.iconAcDet)
        horoscopeLuckTextView = findViewById(R.id.horoscopeLuckTextView)
        bottomNavigationMenu = findViewById(R.id.bottomNavigationMenu)
        linearProgress = findViewById(R.id.linearProgress)

//        findViewById<TextView>(R.id.nameAcDet).text = "${getString(horoscope.name)}"
//        findViewById<TextView>(R.id.dateAcDet).text = "${getString(horoscope.dates)}"
//        findViewById<ImageView>(R.id.iconAcDet).setImageResource(horoscope.icon)
    }

    private fun setFavIcon(){
        if (isFav){
            favoriteMenu.setIcon(R.drawable.ic_fav_selected)
        }else {
            favoriteMenu.setIcon(R.drawable.favorite_empty)
        }

    }

    private fun setHatedIcon(){
        if (isHated){
            favoriteMenu.setIcon(R.drawable.ic_fav_broken)
        }else {
            //buscar icpno vacio
            favoriteMenu.setIcon(R.drawable.favorite_empty)
        }

    }

    private fun getHoroscopeLuck (period: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var urlConnection: HttpsURLConnection? = null

            try {
                val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/$period?sign=${horoscope.id}&day=TODAY")
                urlConnection = url.openConnection() as HttpsURLConnection

                if (urlConnection.responseCode == 200) {
                    val rd = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    var line: String?
                    val stringBuilder = StringBuilder()
                    while ((rd.readLine().also { line = it }) != null) {
                        stringBuilder.append(line)
                    }
                    val result = stringBuilder.toString()

                    // Instantiate a JSON object from the request response
                    val jsonObject = JSONObject(result)
                    val horoscopeLuck = jsonObject.getJSONObject("data").getString("horoscope_data")

                    CoroutineScope(Dispatchers.Main).launch {
                        horoscopeLuckTextView.text = horoscopeLuck
                        linearProgress.visibility = View.GONE
                    }
                    /*runOnUiThread {

                    }*/
                    //Log.i("HTTP", horoscopeLuck)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection?.disconnect()
            }
        }
    }
}