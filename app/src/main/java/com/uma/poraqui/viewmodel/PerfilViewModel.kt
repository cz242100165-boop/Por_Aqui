package com.uma.poraqui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uma.poraqui.Data.model.Usuario
import com.uma.poraqui.Data.repository.UsuarioRepository

class PerfilViewModel : ViewModel() {

    private val repository = UsuarioRepository()

    private val _usuario = MutableLiveData<Usuario>()

    val usuario: LiveData<Usuario>
        get() = _usuario

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    fun cargarUsuario(id: String) {

        repository.obtenerUsuario(
            id = id,
            onSuccess = { usuario ->

                if (usuario != null) {
                    _usuario.value = usuario
                }
            },
            onError = { error ->
                _error.value = error.message
            }
        )
    }
}