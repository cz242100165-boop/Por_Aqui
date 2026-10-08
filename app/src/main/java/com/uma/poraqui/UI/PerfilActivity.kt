package com.uma.poraqui.UI

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uma.poraqui.databinding.ActivityPerfilBinding
import com.uma.poraqui.viewmodel.PerfilViewModel

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    private val viewModel: PerfilViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observarUsuario()

        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserUid != null) {
            viewModel.cargarUsuario(currentUserUid)
        } else {
            // Fallback si no hay sesión activa
            viewModel.cargarUsuario("usuario01")
        }
    }

    private fun observarUsuario() {
        viewModel.usuario.observe(this) { usuario ->
            if (usuario != null) {
                binding.tvNombre.text = usuario.nombre
                binding.tvCorreo.text = "${usuario.correo} · ${usuario.ciudad}"
                binding.tvGuardados.text = usuario.guardados.toString()
                binding.tvResenas.text = usuario.resenas.toString()
                binding.tvVisitas.text = usuario.visitas.toString()
            }
        }
    }
}
