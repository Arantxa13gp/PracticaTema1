package com.example.practicatema1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.practicatema1.ui.theme.PracticaTema1Theme

class PerfilActivity : ComponentActivity() {
    private var nombreState by mutableStateOf("[Nombre]")
    private var apellidosState by mutableStateOf("[Apellidos]")
    private var contraseñaState by mutableStateOf("[Contraseña]")
    private var telefonoState by mutableStateOf("[Telefono]")
    private var correoState by mutableStateOf("[Correo Electronico]")
    private var direccionState by mutableStateOf("[Direccion]")
    private var fechaNacimientoState by mutableStateOf("[Fecha de Nacimiento]")
    private val editarFormularioLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            data?.let {
                // Actualizamos los estados con la información devuelta
                nombreState = it.getStringExtra("NOMBRE") ?: nombreState
                apellidosState = it.getStringExtra("APELLIDOS") ?: apellidosState
                contraseñaState = it.getStringExtra("CONTRASEÑA") ?: contraseñaState
                telefonoState = it.getStringExtra("TELEFONO") ?: telefonoState
                correoState = it.getStringExtra("CORREO") ?: correoState
                direccionState = it.getStringExtra("DIRECCION") ?: direccionState
                fechaNacimientoState = it.getStringExtra("FECHA") ?: fechaNacimientoState
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Asignación inicial desde el Intent de entrada
        nombreState = intent.getStringExtra("NOMBRE") ?: "[Nombre]"
        apellidosState = intent.getStringExtra("APELLIDOS") ?: "[Apellidos]"
        contraseñaState = intent.getStringExtra("CONTRASEÑA") ?: "[Contraseña]"
        telefonoState = intent.getStringExtra("TELEFONO") ?: "[Telefono]"
        correoState = intent.getStringExtra("CORREO") ?: "[Correo Electronico]"
        direccionState = intent.getStringExtra("DIRECCION") ?: "[Direccion]"
        fechaNacimientoState = intent.getStringExtra("FECHA") ?: "[Fecha de Nacimiento]"

        setContent {
            PracticaTema1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter)
                                .padding(bottom = 60.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Contenido(
                                nombre = nombreState,
                                apellidos = apellidosState,
                                contraseña = contraseñaState,
                                telefono = telefonoState,
                                correo = correoState,
                                direccion = direccionState,
                                fechaNacimiento = fechaNacimientoState
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            editarFormulario(
                                modifier = Modifier.weight(1f),
                                onEditarClick = {
                                    // Enviamos los datos actuales para precargarlos en FormularioActivity
                                    val intent = Intent(this@PerfilActivity, FormularioActivity::class.java).apply {
                                        putExtra("NOMBRE", nombreState)
                                        putExtra("APELLIDOS", apellidosState)
                                        putExtra("CONTRASEÑA", contraseñaState)
                                        putExtra("TELEFONO", telefonoState)
                                        putExtra("CORREO", correoState)
                                        putExtra("DIRECCION", direccionState)
                                        putExtra("FECHA", fechaNacimientoState)
                                    }
                                    // Se lanza a través del launcher
                                    editarFormularioLauncher.launch(intent)
                                }
                            )
                            cerrarSesion(modifier = Modifier.weight(1f))
                        }
                    }
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
    val contexto = LocalContext.current

    Column(modifier = modifier.fillMaxWidth()) {
        ItemPerfil(label = "Nombre", valor = nombre)
        ItemPerfil(label = "Apellidos", valor = apellidos)
        ItemPerfil(label = "Contraseña", valor = contraseña)

        ItemPerfil(
            label = "Teléfono",
            valor = telefono,
            esEnlace = true,
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telefono"))
                contexto.startActivity(intent)
            }
        )

        ItemPerfil(
            label = "Correo Electrónico",
            valor = correo,
            esEnlace = true,
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:$correo")
                }
                contexto.startActivity(intent)
            }
        )

        ItemPerfil(
            label = "Dirección",
            valor = direccion,
            esEnlace = true,
            onClick = {
                val encodedAddress = Uri.encode(direccion)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$encodedAddress"))
                contexto.startActivity(intent)
            }
        )

        ItemPerfil(label = "Fecha de Nacimiento", valor = fechaNacimiento)
    }
}

@Composable
fun ItemPerfil(
    label: String,
    valor: String,
    esEnlace: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .then(
                if (esEnlace) Modifier.clickable { onClick() } else Modifier
            )
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = Color.Gray,
                fontSize = TextUnit(12f, TextUnitType.Sp)
            )
        )
        Text(
            text = valor,
            style = TextStyle(
                color = if (esEnlace) Color.Blue else Color.Black,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                textDecoration = if (esEnlace) TextDecoration.Underline else null
            ),
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun editarFormulario(
    modifier: Modifier = Modifier,
    onEditarClick: () -> Unit
) {
    Button(
        onClick = onEditarClick,
        modifier = modifier
    ) {
        Text("Editar", maxLines = 1)
    }
}

@Composable
fun cerrarSesion(modifier: Modifier = Modifier) {
    val contexto = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(contexto, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            contexto.startActivity(intent)
        },
        modifier = modifier
    ) {
        Text("Cerrar Sesion", maxLines = 1)
    }
}