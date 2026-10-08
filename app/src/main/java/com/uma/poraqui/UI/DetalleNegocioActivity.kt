package com.uma.poraqui.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uma.poraqui.databinding.ActivityDetalleNegocioBinding

class DetalleNegocioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleNegocioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityDetalleNegocioBinding.inflate(layoutInflater)

        setContentView(binding.root)

        cargarInformacion()
    }

    private fun cargarInformacion() {

        val nombre =
            intent.getStringExtra("NEGOCIO_NOMBRE")
                ?: "Negocio"

        val categoria =
            intent.getStringExtra("NEGOCIO_CATEGORIA")
                ?: "Comida"

        val descripcion =
            intent.getStringExtra("NEGOCIO_DESCRIPCION")
                ?: "Productos y servicios para toda la familia."

        val telefono =
            intent.getStringExtra("NEGOCIO_TELEFONO")
                ?: "7XXX-1234"

        binding.tvNombre.text = nombre

        binding.tvCategoria.text =
            categoria.uppercase()

        binding.tvDescripcion.text =
            descripcion

        binding.tvTelefono.text =
            telefono

        binding.btnVolver.setOnClickListener {
            finish()
        }

        binding.btnMapa.setOnClickListener {

            startActivity(
                android.content.Intent(
                    this,
                    MapaActivity::class.java
                )
            )
        }
    }
}