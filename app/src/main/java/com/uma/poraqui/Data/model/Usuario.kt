package com.uma.poraqui.Data.model

data class Usuario(
    val id: String = "",
    val nombre: String = "",
    val correo: String = "",
    val ciudad: String = "Sonsonate",
    val guardados: Int = 0,
    val resenas: Int = 0,
    val visitas: Int = 0
)