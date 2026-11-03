package com.sistemasop.snippets

import java.io.File

private val snippets = mapOf(
    "MANZANA" to "\nif (condicion) {\n\n}\n",
    "PERA" to "\nfor (i in 0 until 10) {\n\n}\n",
    "GATO" to "\nwhen (variable) {\n\n}\n",
    "AZUL" to "\nwhile (condicion) {\n\n}\n",
    "24130803" to "Juan Pablo Rodriguez Hurtado "
)

fun autoGuardado(texto: String){
    val archivo = File(
        System.getProperty("user.home") + "/Downloads/archivo.txt"
    )
    archivo.writeText(texto)
}

fun ejecutarSnippets(texto: String): String {
    val patron = Regex(
        """\b(MANZANA|PERA|GATO|AZUL|24130803)\b""",
        RegexOption.IGNORE_CASE
    )

    return patron.replace(texto) {
        snippets[it.value.uppercase()] ?: it.value
    }
}
