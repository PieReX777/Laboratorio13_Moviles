package com.example.lab13

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBoxScreen(modifier: Modifier = Modifier) {
    // Estado para manejar la visibilidad
    var isVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Botón para alternar la visibilidad
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = if (isVisible) "Ocultar cuadro" else "Mostrar cuadro")
        }

        // Animación de visibilidad para el cuadro
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Blue)
            )
        }
    }
}

@Composable
fun ColorChangingBoxScreen(modifier: Modifier = Modifier) {
    var isBlue by remember { mutableStateOf(true) }
    var useSpringAnimation by remember { mutableStateOf(false) }

    // Define la especificación de la animación
    val animationSpec: AnimationSpec<Color> = if (useSpringAnimation) {
        spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessMedium)
    } else {
        tween(durationMillis = 1000)
    }

    // Animación del color de fondo
    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = animationSpec
    )

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón para cambiar el color
        Button(onClick = { isBlue = !isBlue }) {
            Text(text = "Cambiar Color")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para cambiar entre animación Tween y Spring
        Button(onClick = { useSpringAnimation = !useSpringAnimation }) {
            Text(text = if (useSpringAnimation) "Usar Tween" else "Usar Spring")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Caja con el color animado
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(backgroundColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ColorChangingBoxScreenPreview() {
    ColorChangingBoxScreen()
}