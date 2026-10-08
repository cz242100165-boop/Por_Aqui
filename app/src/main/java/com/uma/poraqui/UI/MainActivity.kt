package com.uma.poraqui.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uma.poraqui.R
import com.uma.poraqui.adapter.NegocioAdapter
import com.uma.poraqui.databinding.ActivityMainBinding
import com.uma.poraqui.viewmodel.InicioViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: InicioViewModel by viewModels()

    private lateinit var adapter: NegocioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        configurarRecyclerView()

        observarDatos()

        configurarBotones()

        viewModel.cargarNegocios()
    }

    private fun configurarRecyclerView() {

        adapter = NegocioAdapter(
            emptyList()
        ) { negocio ->

            val intent = Intent(
                this,
                DetalleNegocioActivity::class.java
            )

            intent.putExtra(
                "NEGOCIO_ID",
                negocio.id
            )

            intent.putExtra(
                "NEGOCIO_NOMBRE",
                negocio.Nombre
            )

            intent.putExtra(
                "NEGOCIO_CATEGORIA",
                negocio.Categoria
            )

            intent.putExtra(
                "NEGOCIO_DESCRIPCION",
                negocio.Descripcion
            )

            intent.putExtra(
                "NEGOCIO_TELEFONO",
                negocio.Telefono
            )

            startActivity(intent)
        }

        binding.rvNegocios.layoutManager =
            LinearLayoutManager(this)

        binding.rvNegocios.adapter = adapter
    }

    private fun observarDatos() {

        viewModel.negocios.observe(this) { lista ->

            adapter.actualizarLista(
                lista.take(3)
            )
        }

        viewModel.error.observe(this) { mensaje ->

            if (!mensaje.isNullOrEmpty()) {

                Toast.makeText(
                    this,
                    mensaje,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun configurarBotones() {

        binding.btnExplorar.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ExplorarActivity::class.java
                )
            )
        }

        binding.btnMapa.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    MapaActivity::class.java
                )
            )
        }

        binding.btnPerfil.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    PerfilActivity::class.java
                )
            )
        }
    }
}