package com.sistemasop.snippets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextRange
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import java.util.concurrent.Executors

@Composable
fun interfaz() {
    var texto by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val neon = Color(0xFFBF00FF)
    val ejecutor = remember {
        Executors.newSingleThreadExecutor()
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Implementación de la interfaz
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = neon,
                spotColor = neon
            )
            .background(Color.Black)
            .border(
                width = 2.dp,
                color = neon,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            text = "Bloc de Notas",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.White
        )

        DisposableEffect(ejecutor) {
            onDispose {
                ejecutor.shutdown()
            }
        }

        OutlinedTextField(
            value = texto,

            onValueChange = { nuevoValor ->

                val anterior = texto.text
                val nuevoTexto = nuevoValor.text
                val cursor = nuevoValor.selection.end

                texto = nuevoValor

                // Detectar que se insertó un separador
                val palabraTerminada =
                    cursor > 0 &&
                            nuevoTexto.length == anterior.length + 1 &&
                            nuevoTexto[cursor - 1].isWhitespace() &&
                            nuevoTexto.removeRange(cursor - 1, cursor) == anterior

                if (palabraTerminada) {

                    val palabra = nuevoTexto
                        .substring(0, cursor - 1)
                        .takeLastWhile {
                            it.isLetterOrDigit() || it == '_'
                        }

                    if (palabra.isNotEmpty()) {

                        // Ejecutar snippets
                        val resultado = ejecutarSnippets(nuevoTexto)

                        if (resultado != nuevoTexto) {

                            // Actualizar texto y cursor
                            texto = TextFieldValue(
                                text = resultado,
                                selection = TextRange(resultado.length)
                            )
                        }

                        // Autoguardado en hilo secundario
                        ejecutor.execute {
                            autoGuardado(resultado)
                        }
                    }
                }
            },

            placeholder = {
                Text("Ingrese su texto aquí")
            },

            minLines = 5,

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .fillMaxHeight(0.75f)
                .shadow(
                    elevation = 15.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = neon,
                    spotColor = neon
                )
                .border(
                    width = 2.dp,
                    color = neon,
                    shape = RoundedCornerShape(12.dp)
                ),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            )
        )

        Button(
            onClick = {
                texto = TextFieldValue("")
            },
            interactionSource = interactionSource,

            shape = RoundedCornerShape(12.dp),

            border = BorderStroke(
                width = 2.dp,
                color = neon
            ),

            colors = ButtonDefaults.buttonColors(
                containerColor = if (isHovered) neon else Color.Black,
                contentColor = if (isHovered) Color.Black else neon
            )
        ) {
            Text(
                text = "Borrar",
                color = if (isHovered) Color.Black else neon,
                fontSize = 18.sp
            )
        }
    }
}