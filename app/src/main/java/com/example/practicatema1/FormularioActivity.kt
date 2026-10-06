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
import java.sql.Date

class FormularioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PracticaTema1Theme {
                var nombre by remember { mutableStateOf("") }
                var apellidos by remember { mutableStateOf("") }
                var contraseña by remember { mutableStateOf("") }
                var telefono by remember { mutableStateOf("") }
                var correo by remember { mutableStateOf("") }
                var direccion by remember { mutableStateOf("") }
                var fechaNacimiento by remember { mutableStateOf("") }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding)
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState())
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
                            nombre = nombre,
                            apellidos = apellidos,
                            contraseña = contraseña,
                            telefono = telefono,
                            correo = correo,
                            direccion = direccion,
                            fechaNacimiento = fechaNacimiento
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
        Text("Nombre")
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Apellidos")
        OutlinedTextField(
            value = apellidos,
            onValueChange = onApellidosChange,
            label = { Text("Apellidos") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Contraseña")
        OutlinedTextField(
            value = contraseña,
            onValueChange = onContraseñaChange,
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Telefono")
        OutlinedTextField(
            value = telefono,
            onValueChange = onTelefonoChange,
            label = { Text("Telefono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Correo Electronico")
        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange,
            label = { Text("Correo Electronico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Direccion")
        OutlinedTextField(
            value = direccion,
            onValueChange = onDireccionChange,
            label = { Text("Direccion") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Fecha de Nacimiento")
        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = onFechaNacimientoChange,
            label = { Text("Fecha de Nacimiento") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}

@Composable
fun abrirPerfil(
    nombre: String,
    apellidos: String,
    contraseña: String,
    telefono: String,
    correo: String,
    direccion: String,
    fechaNacimiento: String
){
    val contexto = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = {
                val intent = Intent(contexto, PerfilActivity::class.java).apply {
                    putExtra("NOMBRE", nombre)
                    putExtra("APELLIDOS", apellidos)
                    putExtra("CONTRASEÑA", contraseña)
                    putExtra("TELEFONO", telefono)
                    putExtra("CORREO", correo)
                    putExtra("DIRECCION", direccion)
                    putExtra("FECHA", fechaNacimiento)
                }
                contexto.startActivity(intent)
            }
        ) {
            Text("Ver perfil")
        }
    }
}