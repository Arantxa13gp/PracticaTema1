package com.example.practicatema1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.practicatema1.FormularioActivity
import com.example.practicatema1.abrirFormulario
import com.example.practicatema1.ui.theme.PracticaTema1Theme
import com.example.practicatema1.ui.theme.PracticaTema1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaTema1Theme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    abrirFormulario(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun abrirFormulario(modifier: Modifier = Modifier) {
    val contexto = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                val intent = Intent(contexto, FormularioActivity::class.java)
                contexto.startActivity(intent)
            },
            modifier = modifier
        ) {
            Text("Ir a formulario")
        }
    }
}
