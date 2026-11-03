package com.sistemasop.snippets

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform