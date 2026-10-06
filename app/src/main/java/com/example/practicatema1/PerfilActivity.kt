package com.example.practicatema1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.practicatema1.ui.theme.PracticaTema1Theme

class PerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val nombre = intent.getStringExtra("NOMBRE") ?: "[Nombre]"
        val apellidos = intent.getStringExtra("APELLIDOS") ?: "[Apellidos]"
        val contraseña = intent.getStringExtra("CONTRASEÑA") ?: "[Contraseña]"
        val telefono = intent.getStringExtra("TELEFONO") ?: "[Telefono]"
        val correo = intent.getStringExtra("CORREO") ?: "[Correo Electronico]"
        val direccion = intent.getStringExtra("DIRECCION") ?: "[Direccion]"
        val fechaNacimiento = intent.getStringExtra("FECHA") ?: "[Fecha de Nacimiento]"

        setContent {
            PracticaTema1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding)
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Contenido(
                            nombre = nombre,
                            apellidos = apellidos,
                            contraseña = contraseña,
                            telefono = telefono,
                            correo = correo,
                            direccion = direccion,
                            fechaNacimiento = fechaNacimiento
                        )
                    }
                    editarFormulario()
                }
            }
        }
    }
}

@Composable
fun Contenido(
    nombre: String,
    apellidos: String,
    contraseña: String,
    telefono: String,
    correo: String,
    direccion: String,
    fechaNacimiento: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = nombre,
            onValueChange = {},
            label = { Text("Nombre") },
            readOnly = true, // Para que funcione como campo de solo lectura en el perfil
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = apellidos,
            onValueChange = {},
            label = { Text("Apellidos") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            singleLine = true
        )

        Text("Contraseña")
        OutlinedTextField(
            value = contraseña,
            onValueChange = {},
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = {},
            label = { Text("Telefono") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = correo,
            onValueChange = {},
            label = { Text("Correo Electronico") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = {},
            label = { Text("Direccion") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = {},
            label = { Text("Fecha de Nacimiento") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            singleLine = true
        )
    }
}

@Composable
fun editarFormulario(modifier: Modifier = Modifier) {
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
            Text("Editar Formulario")
        }
    }
}