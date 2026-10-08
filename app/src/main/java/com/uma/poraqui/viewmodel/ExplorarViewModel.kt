package com.uma.poraqui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uma.poraqui.Data.model.Negocio
import com.uma.poraqui.Data.repository.NegocioRepository

class ExplorarViewModel : ViewModel() {

    private val repository = NegocioRepository()

    private val _Negocios = MutableLiveData<List<Negocio>>()

    val Negocios: LiveData<List<Negocio>>
        get() = _Negocios

    fun cargarNegocios() {

        repository.obtenerNegocios(
            onSuccess = { lista ->
                _Negocios.value = lista
            },
            onError = {
                _Negocios.value = emptyList()
            }
        )
    }

    fun buscar(texto: String) {

        val listaActual = _Negocios.value ?: return

        val resultado = listaActual.filter {

            it.Nombre.contains(texto, ignoreCase = true) ||
                    it.Categoria.contains(texto, ignoreCase = true)
        }

        _Negocios.value = resultado
    }

    fun filtrarCategoria(categoria: String) {

        if (categoria == "Todos") {
            cargarNegocios()
            return
        }

        val listaActual = _Negocios.value ?: return

        _Negocios.value = listaActual.filter {
            it.Categoria.equals(categoria, ignoreCase = true)
        }
    }
}