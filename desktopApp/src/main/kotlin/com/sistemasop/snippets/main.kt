package com.sistemasop.snippets

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Bloc_de_Notas",
    ) {
        interfaz()
    }
}