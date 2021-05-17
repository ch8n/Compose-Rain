package domain

import androidx.compose.ui.graphics.Color

object Window {
    val DEBUG = false
    val WIDTH = if (DEBUG) 800 else 1200
    val HEIGHT = if (DEBUG) 400 else 800
}

val backgroundColor = Color(0xffe6e6fa)
val dropColor = Color(0xff8a2be2)
