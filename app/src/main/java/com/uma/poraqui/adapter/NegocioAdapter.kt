package com.uma.poraqui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uma.poraqui.R
import com.uma.poraqui.Data.model.Negocio

class NegocioAdapter(
    private var lista: List<Negocio>,
    private val onClick: (Negocio) -> Unit
) : RecyclerView.Adapter<NegocioAdapter.NegocioViewHolder>() {

    class NegocioViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvIcono: TextView =
            itemView.findViewById(R.id.tvIcono)

        val tvNombre: TextView =
            itemView.findViewById(R.id.tvNombre)

        val tvCategoria: TextView =
            itemView.findViewById(R.id.tvCategoria)

        val tvDistancia: TextView =
            itemView.findViewById(R.id.tvDistancia)

        val tvEstado: TextView =
            itemView.findViewById(R.id.tvEstado)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NegocioViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_negocio,
                parent,
                false
            )

        return NegocioViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: NegocioViewHolder,
        position: Int
    ) {

        val negocio = lista[position]

        holder.tvIcono.text = obtenerIcono(negocio.Categoria)
        holder.tvNombre.text = negocio.Nombre

        holder.tvCategoria.text =
            "★ ${negocio.Calificacion} · ${negocio.Categoria}"

        holder.tvDistancia.text =
            "📍 ${negocio.Distancia} · ${negocio.Direccion}"

        holder.tvEstado.text =
            if (negocio.Abierto) {
                "• Abierto"
            } else {
                "• Cerrado"
            }

        holder.itemView.setOnClickListener {
            onClick(negocio)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun actualizarLista(nuevaLista: List<Negocio>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

    private fun obtenerIcono(categoria: String): String {

        return when (categoria.lowercase()) {

            "comida" -> "🍔"

            "cafeterías",
            "cafeterias" -> "☕"

            "belleza" -> "💇"

            "tiendas" -> "🛒"

            "servicios" -> "🔧"

            "ropa" -> "👗"

            "hospedaje" -> "🏨"

            else -> "✨"
        }
    }
}