package com.uma.poraqui.Data.repository

import com.uma.poraqui.Data.model.Negocio
import com.google.firebase.firestore.FirebaseFirestore

class NegocioRepository {

    private val db = FirebaseFirestore.getInstance()

    fun obtenerNegocios(
        onSuccess: (List<Negocio>) -> Unit,
        onError: (Exception) -> Unit
    ) {

        db.collection("negocios")
            .get()
            .addOnSuccessListener { resultado ->

                val lista = resultado.documents.mapNotNull { documento ->

                    documento.toObject(Negocio::class.java)?.copy(
                        id = documento.id
                    )
                }

                onSuccess(lista)
            }
            .addOnFailureListener { error ->
                onError(error)
            }
    }

    fun obtenerNegocio(
        id: String,
        onSuccess: (Negocio?) -> Unit,
        onError: (Exception) -> Unit
    ) {

        db.collection("negocios")
            .document(id)
            .get()
            .addOnSuccessListener { documento ->

                val negocio = documento.toObject(Negocio::class.java)

                onSuccess(
                    negocio?.copy(id = documento.id)
                )
            }
            .addOnFailureListener { error ->
                onError(error)
            }
    }
}