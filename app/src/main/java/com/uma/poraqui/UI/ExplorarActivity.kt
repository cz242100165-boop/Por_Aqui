package com.uma.poraqui.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uma.poraqui.adapter.NegocioAdapter
import com.uma.poraqui.databinding.ActivityExplorarBinding
import com.uma.poraqui.viewmodel.ExplorarViewModel

class ExplorarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExplorarBinding

    private val viewModel: ExplorarViewModel by viewModels()

    private lateinit var adapter: NegocioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityExplorarBinding.inflate(layoutInflater)

        setContentView(binding.root)

        configurarRecyclerView()

        observarDatos()

        configurarBusqueda()

        viewModel.cargarNegocios()
    }

    private fun configurarRecyclerView() {

        adapter = NegocioAdapter(emptyList()) { negocio ->

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

        viewModel.Negocios.observe(this) { lista ->

            adapter.actualizarLista(lista)
        }
    }

    private fun configurarBusqueda() {

        binding.btnBuscar.setOnClickListener {

            val texto =
                binding.etBuscar.text.toString()

            viewModel.buscar(texto)
        }

        binding.btnTodos.setOnClickListener {
            viewModel.cargarNegocios()
        }

        binding.btnComida.setOnClickListener {
            viewModel.filtrarCategoria("Comida")
        }

        binding.btnCafeterias.setOnClickListener {
            viewModel.filtrarCategoria("Cafeterías")
        }

        binding.btnBelleza.setOnClickListener {
            viewModel.filtrarCategoria("Belleza")
        }

        binding.btnTiendas.setOnClickListener {
            viewModel.filtrarCategoria("Tiendas")
        }
    }
}