package com.uma.poraqui.Data.repository

import com.uma.poraqui.Data.model.Usuario
import com.google.firebase.firestore.FirebaseFirestore

class UsuarioRepository {

    private val db = FirebaseFirestore.getInstance()

    fun obtenerUsuario(
        id: String,
        onSuccess: (Usuario?) -> Unit,
        onError: (Exception) -> Unit
    ) {

        db.collection("usuarios")
            .document(id)
            .get()
            .addOnSuccessListener { documento ->

                val usuario = documento.toObject(Usuario::class.java)

                onSuccess(
                    usuario?.copy(id = documento.id)
                )
            }
            .addOnFailureListener { error ->
                onError(error)
            }
    }

    fun guardarUsuario(
        usuario: Usuario,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        db.collection("usuarios")
            .document(usuario.id)
            .set(usuario)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { error ->
                onError(error)
            }
    }
}