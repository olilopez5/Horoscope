package com.example.horoscope

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.horoscope.HoroscopeAdapter.HoroscopeViewHolder



class HoroscopeAdapter(var items : List<Horoscope>, val onClick : (Int) -> Unit) : Adapter<HoroscopeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int {
        //pasa el tamaño de las lista que le pasamos
        return items.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = items[position]
        holder.render(horoscope)

        //vista raiz de toda la celda, definido en view holder
        holder.itemView.setOnClickListener {
            onClick(position)
            // depende del activity no del adapter para redireccionar, adapter recoge el click , es una buena práctica
        }
    }

    fun updateIt(items: List<Horoscope>) {
        //asignar nuevos elementos
        this.items = items
        //actualizar los cambios, hay una nueva lista el recycler view
        notifyDataSetChanged()
    }


    class HoroscopeViewHolder(view: View) : ViewHolder(view) {

        val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        //val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val favImageView: ImageView = view.findViewById(R.id.favImageView)

        fun render(horoscope: Horoscope) {
            iconImageView.setImageResource(horoscope.icon)
            nameTextView.setText(horoscope.name)
            //dateTextView.setText(horoscope.dates)

            if (SessionManager(itemView.context).isFav(horoscope.id)) {
                favImageView.visibility = View.VISIBLE
            } else {
                favImageView.visibility = View.GONE
            }
        }
    }
}