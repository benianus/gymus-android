package com.example.gymusAndroid.core.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

var roundedShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(32.dp),
    extraLarge = RoundedCornerShape(64.dp)
)

var connerShapes = Shapes(
    extraSmall = CutCornerShape(4.dp),
    small = CutCornerShape(8.dp),
    medium = CutCornerShape(16.dp),
    large = CutCornerShape(32.dp),
    extraLarge = CutCornerShape(64.dp)
)