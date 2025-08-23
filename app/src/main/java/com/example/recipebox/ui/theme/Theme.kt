package com.example.recipebox.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = White,
    primaryContainer = PrimaryBaseDark,
    onPrimaryContainer = PrimaryLight,
    secondary = SecondaryDark,
    onSecondary = White,
    secondaryContainer = SecondaryBaseDark,
    onSecondaryContainer = SecondaryLight,
    tertiary = TertiaryDark,
    onTertiary = Black,
    tertiaryContainer = TertiaryBaseDark,
    onTertiaryContainer = TertiaryLight,
    background = PrimaryBaseDarkest,
    onBackground = White,
    surface = PrimaryBaseDarker,
    onSurface = White,
    surfaceVariant = DarkGray,
    onSurfaceVariant = LightGray,
    error = Error,
    onError = White,
    errorContainer = ErrorLight,
    onErrorContainer = Black
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBase,
    onPrimary = White,
    primaryContainer = PrimaryLight,
    onPrimaryContainer = PrimaryBaseDarkest,
    secondary = SecondaryBase,
    onSecondary = White,
    secondaryContainer = SecondaryLight,
    onSecondaryContainer = SecondaryBaseDarkest,
    tertiary = TertiaryBase,
    onTertiary = Black,
    tertiaryContainer = TertiaryLight,
    onTertiaryContainer = TertiaryBaseDarkest,
    background = Background,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = LighterGray,
    onSurfaceVariant = DarkestGray,
    error = Error,
    onError = White,
    errorContainer = ErrorLight,
    onErrorContainer = Black
)

@Composable
fun RecipeBoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}