package com.uma.poraqui.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uma.poraqui.databinding.ActivityMapaBinding

class MapaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityMapaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnVolver.setOnClickListener {
            finish()
        }
    }
}