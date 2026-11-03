# Bloc de Notas Inteligente

Este es un bloc de notas que utiliza hilos de procesamiento para realizar 3 tareas fundamentales aparte de su funcion de escribir normalmente como cualquier bloc de notas.

## 1. Auto Guardado

Va a utilizar un hilo de ejecucion que detecte en que momento se realiza un cambio en el contenido del documento, despues, guardara el contenido del documento en un archivo.txt sin necesidad del usuario para guardarlo directamente ni manualmente

## 2. Control

Al escribir mi numero de control, el bloc de notas va a reemplazar el contenido del texto con mi nombre completo mas mi numero de control a modo de un snipped

## 3. Snipped

Utilizando diferentes snippets el bloc de notas reemplazara palabras clave con bloques de codigo siguiendo la estructura del lenguaje kotlin (usado para desarrollar esta aplicacion) de manera coherente y sin hacer que la aplicacion deje de funcionar, los snippeds seran:

- Manzana: Inserta un bucle if de la siguiente forma:

```kotlin
if (condicion) {
    // codigo
}
```

- Pera: Inserta un bucle for de la siguiente forma:

```kotlin
for (i in 0 until 10) {
    // codigo
}
```

- Gato: Inserta una estructura Switch (When en kotlin) de la siguiente forma:

```kotlin
when (variable) {
    1 -> {
        // codigo
    }
    2 -> {
        // codigo
    }
}
```

- Azul: Inserta un bucle while de la siguiente forma:

```kotlin
while(true){
    //codigo
}
```

**Nota: No importara si es mayuscula o minuscula**