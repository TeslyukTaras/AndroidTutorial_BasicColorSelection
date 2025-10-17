package com.teslyuk.android.androidtutorial.basiccolorselection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ColorPickerScreen()
            }
        }
    }
}

@Composable
fun ColorPickerScreen() {
    var red by remember { mutableIntStateOf(0) }
    var green by remember { mutableIntStateOf(0) }
    var blue by remember { mutableIntStateOf(0) }

    val currentColor = Color(red, green, blue)
    val hex = String.format("#%02x%02x%02x", red, green, blue)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Color",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // ==== Sliders ====
        ColorSlider("Red", red, Color.Red) { red = it }
        ColorSlider("Green", green, Color.Green) { green = it }
        ColorSlider("Blue", blue, Color.Blue) { blue = it }

        Spacer(Modifier.height(12.dp))

        Text(
            text = "$red / $green / $blue = $hex",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        // ==== Color buttons ====
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val presets = listOf(
                Triple(255, 0, 0) to Color.Red,
                Triple(255, 127, 0) to Color(255, 127, 0),
                Triple(255, 255, 0) to Color.Yellow,
                Triple(0, 255, 0) to Color.Green,
                Triple(0, 0, 255) to Color.Blue,
                Triple(75, 0, 130) to Color(75, 0, 130),
                Triple(143, 0, 255) to Color(143, 0, 255)
            )
            presets.forEach { (rgb, color) ->
                ColorButton(color = color) {
                    red = rgb.first
                    green = rgb.second
                    blue = rgb.third
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // ==== Preview box ====
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(currentColor, RoundedCornerShape(12.dp))
        )
    }
}

@Composable
fun ColorSlider(label: String, value: Int, color: Color, onValueChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = color,
            modifier = Modifier.width(80.dp)
        )
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
            valueRange = 0f..255f,
            modifier = Modifier.weight(1f),
            colors = SliderDefaults.colors(
                thumbColor = color,
                activeTrackColor = color
            )
        )
    }
}

@Composable
fun ColorButton(color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {}
}
