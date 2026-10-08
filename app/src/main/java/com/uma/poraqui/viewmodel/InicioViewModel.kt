package com.uma.poraqui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uma.poraqui.Data.model.Negocio
import com.uma.poraqui.Data.repository.NegocioRepository

class InicioViewModel : ViewModel() {

    private val repository = NegocioRepository()

    private val _negocios = MutableLiveData<List<Negocio>>()

    val negocios: LiveData<List<Negocio>>
        get() = _negocios

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    fun cargarNegocios() {

        repository.obtenerNegocios(
            onSuccess = { lista ->
                _negocios.value = lista
            },
            onError = { error ->
                _error.value = error.message
            }
        )
    }
}