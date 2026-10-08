package com.uma.poraqui.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uma.poraqui.Data.model.Usuario
import com.uma.poraqui.Data.repository.UsuarioRepository
import com.uma.poraqui.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth
    private val usuarioRepository = UsuarioRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegistrar.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val email = binding.etCorreo.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    val uid = authResult.user?.uid ?: ""
                    val nuevoUsuario = Usuario(
                        id = uid,
                        nombre = nombre,
                        correo = email,
                        ciudad = "Sonsonate",
                        guardados = 0,
                        resenas = 0,
                        visitas = 0
                    )

                    usuarioRepository.guardarUsuario(
                        usuario = nuevoUsuario,
                        onSuccess = {
                            Toast.makeText(this, "¡Cuenta creada con éxito!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finishAffinity()
                        },
                        onError = { e ->
                            Toast.makeText(this, "Error al guardar perfil: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                    )
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al registrarse: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
        }

        binding.btnVolver.setOnClickListener {
            finish()
        }
    }
}
