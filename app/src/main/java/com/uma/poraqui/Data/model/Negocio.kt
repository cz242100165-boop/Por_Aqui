package com.uma.poraqui.Data.model

data class Negocio(
    val id: String = "",
    val Nombre: String = "",
    val Categoria: String = "",
    val Descripcion: String = "",
    val Direccion: String = "",
    val Telefono: String = "",
    val Horario: String = "",
    val Calificacion: Double = 0.0,
    val Distancia: String = "",
    val Abierto: Boolean = true,
    val Imagen: String = ""
)