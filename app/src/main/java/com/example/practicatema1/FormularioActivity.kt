package com.example.practicatema1
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
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
import androidx.compose.ui.unit.dp
import com.example.practicatema1.ui.theme.PracticaTema1Theme

class FormularioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PracticaTema1Theme() {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    formulario(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun formulario(modifier: Modifier = Modifier){
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }


    Column(
        modifier = modifier.fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Nombre")
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Apellidos")
        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Contraseña")
        OutlinedTextField(
            value = contrasena,
            onValueChange = {contrasena = it},
            label = {Text("Contraseña")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Telefono")
        OutlinedTextField(
            value = telefono,
            onValueChange = {telefono = it},
            label = {Text("Telefono")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Correo Electronico")
        OutlinedTextField(
            value = correo,
            onValueChange = {correo = it},
            label = {Text("Correo Electronico")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Direccion")
        OutlinedTextField(
            value = direccion,
            onValueChange = {direccion = it},
            label = {Text("Direccion")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("Fecha de Nacimiento")
        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = {fechaNacimiento = it},
            label = {Text("Fecha de Nacimiento")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
    abrirPerfil()
}
@Composable
fun abrirPerfil(){

    val contexto = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = {
                val intent = Intent(contexto, PerfilActivity::class.java)
                contexto.startActivity(intent)
            }
        ) {
            Text("Ver perfil")
        }
    }
}

