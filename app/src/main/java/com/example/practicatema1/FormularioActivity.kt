package com.example.practicatema1

import android.app.Activity
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.practicatema1.ui.theme.PracticaTema1Theme

class FormularioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val initialNombre = intent.getStringExtra("NOMBRE") ?: ""
        val initialApellidos = intent.getStringExtra("APELLIDOS") ?: ""
        val initialContraseña = intent.getStringExtra("CONTRASEÑA") ?: ""
        val initialTelefono = intent.getStringExtra("TELEFONO") ?: ""
        val initialCorreo = intent.getStringExtra("CORREO") ?: ""
        val initialDireccion = intent.getStringExtra("DIRECCION") ?: ""
        val initialFechaNacimiento = intent.getStringExtra("FECHA") ?: ""

        setContent {
            PracticaTema1Theme {
                var nombre by remember { mutableStateOf(initialNombre) }
                var apellidos by remember { mutableStateOf(initialApellidos) }
                var contraseña by remember { mutableStateOf(initialContraseña) }
                var telefono by remember { mutableStateOf(initialTelefono) }
                var correo by remember { mutableStateOf(initialCorreo) }
                var direccion by remember { mutableStateOf(initialDireccion) }
                var fechaNacimiento by remember { mutableStateOf(initialFechaNacimiento) }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding)
                            .padding(20.dp)
                    ) {
                        formulario(
                            nombre = nombre, onNombreChange = { nombre = it },
                            apellidos = apellidos, onApellidosChange = { apellidos = it },
                            contraseña = contraseña, onContraseñaChange = { contraseña = it },
                            telefono = telefono, onTelefonoChange = { telefono = it },
                            correo = correo, onCorreoChange = { correo = it },
                            direccion = direccion, onDireccionChange = { direccion = it },
                            fechaNacimiento = fechaNacimiento, onFechaNacimientoChange = { fechaNacimiento = it }
                        )
                        abrirPerfil(
                            onClick = {
                                if (callingActivity != null) {
                                    val resultIntent = Intent().apply {
                                        putExtra("NOMBRE", nombre)
                                        putExtra("APELLIDOS", apellidos)
                                        putExtra("CONTRASEÑA", contraseña)
                                        putExtra("TELEFONO", telefono)
                                        putExtra("CORREO", correo)
                                        putExtra("DIRECCION", direccion)
                                        putExtra("FECHA", fechaNacimiento)
                                    }
                                    setResult(Activity.RESULT_OK, resultIntent)
                                    finish()
                                } else {
                                    val intent = Intent(this@FormularioActivity, PerfilActivity::class.java).apply {
                                        putExtra("NOMBRE", nombre)
                                        putExtra("APELLIDOS", apellidos)
                                        putExtra("CONTRASEÑA", contraseña)
                                        putExtra("TELEFONO", telefono)
                                        putExtra("CORREO", correo)
                                        putExtra("DIRECCION", direccion)
                                        putExtra("FECHA", fechaNacimiento)
                                    }
                                    startActivity(intent)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun formulario(
    nombre: String, onNombreChange: (String) -> Unit,
    apellidos: String, onApellidosChange: (String) -> Unit,
    contraseña: String, onContraseñaChange: (String) -> Unit,
    telefono: String, onTelefonoChange: (String) -> Unit,
    correo: String, onCorreoChange: (String) -> Unit,
    direccion: String, onDireccionChange: (String) -> Unit,
    fechaNacimiento: String, onFechaNacimientoChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = nombre,
            label = { Text("Nombre") },
            onValueChange = onNombreChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = apellidos,
            label = { Text("Apellidos") },
            onValueChange = onApellidosChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = contraseña,
            label = { Text("Contraseña") },
            onValueChange = onContraseñaChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = telefono,
            label = { Text("Telefono") },
            onValueChange = onTelefonoChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = correo,
            label = { Text("Correo Electronico") },
            onValueChange = onCorreoChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = direccion,
            label = { Text("Direccion") },
            onValueChange = onDireccionChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = fechaNacimiento,
            label = { Text("Fecha de Nacimiento") },
            onValueChange = onFechaNacimientoChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}

@Composable
fun abrirPerfil(
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.width(130.dp)
        ) {
            Text("Ver Perfil")
        }
    }
}