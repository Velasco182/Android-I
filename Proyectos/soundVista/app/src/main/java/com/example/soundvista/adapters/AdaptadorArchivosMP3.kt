package com.example.soundvista.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soundvista.R
import com.example.soundvista.data.archivoMP3

class AdaptadorArchivosMP3(var archivosMP3: List<archivoMP3>,
                           private val onArchivoClickListener: (archivoMP3: archivoMP3) -> Unit
) : RecyclerView.Adapter<AdaptadorArchivosMP3.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    fun actualizarArchivos(nuevaLista: List<archivoMP3>) {
        archivosMP3 = nuevaLista
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_archivo_mp3, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nombreArchivo: TextView = holder.itemView.findViewById(R.id.nombreArchivo)
        val archivoMP3 = archivosMP3[position]
        nombreArchivo.text = archivoMP3.nombre

        holder.itemView.setOnClickListener{
            onArchivoClickListener.invoke(archivoMP3)
        }
    }

    override fun getItemCount(): Int {
        return archivosMP3.size
    }
}
