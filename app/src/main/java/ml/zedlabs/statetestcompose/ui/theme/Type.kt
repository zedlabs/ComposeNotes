package ml.zedlabs.statetestcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ml.zedlabs.statetestcompose.R

// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val robotoCus = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.roboto_light, weight = FontWeight.W300),
            )
        ),
        fontWeight = FontWeight.SemiBold
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.roboto_thin, weight = FontWeight.W300),
            )
        )
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.roboto_light_italic, weight = FontWeight.W300)
            )
        ),
        color = Color.Gray
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.comfortaa_bold, weight = FontWeight.W300)
            )
        )
    )
)