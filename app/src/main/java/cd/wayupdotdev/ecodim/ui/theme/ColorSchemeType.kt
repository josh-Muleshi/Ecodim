package cd.wayupdotdev.ecodim.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

object EcodimColorScheme {

    val light = getColorScheme(Color.Light)
    val dark = getColorScheme(Color.Dark)

    private fun getColorScheme(type: ColorSchemeType): ColorScheme {
        return ColorScheme(
            primary = type.Primary,
            onPrimary = type.OnPrimary,
            primaryContainer = type.PrimaryContainer,
            onPrimaryContainer = type.OnPrimaryContainer,
            inversePrimary = type.InversePrimary,
            secondary = type.Secondary,
            onSecondary = type.OnSecondary,
            secondaryContainer = type.SecondaryContainer,
            onSecondaryContainer = type.OnSecondaryContainer,
            tertiary = type.Tertiary,
            onTertiary = type.OnTertiary,
            tertiaryContainer = type.TertiaryContainer,
            onTertiaryContainer = type.OnTertiaryContainer,
            background = type.Background,
            onBackground = type.OnBackground,
            surface = type.Surface,
            onSurface = type.OnSurface,
            surfaceVariant = type.SurfaceVariant,
            onSurfaceVariant = type.OnSurfaceVariant,
            surfaceTint = type.SurfaceTint,
            inverseSurface = type.InverseSurface,
            inverseOnSurface = type.InverseOnSurface,
            error = type.Error,
            onError = type.OnError,
            errorContainer = type.ErrorContainer,
            onErrorContainer = type.OnErrorContainer,
            outline = type.Outline,
            outlineVariant = type.OutlineVariant,
            scrim = type.scrim
        )
    }

}

interface ColorSchemeType  {
    val Primary : Color
    val OnPrimary : Color
    val PrimaryContainer: Color
    val OnPrimaryContainer: Color
    val InversePrimary: Color
    val Secondary: Color
    val OnSecondary: Color
    val SecondaryContainer: Color
    val OnSecondaryContainer: Color
    val Tertiary : Color
    val OnTertiary: Color
    val TertiaryContainer: Color
    val OnTertiaryContainer: Color
    val Background: Color
    val OnBackground: Color
    val Surface : Color
    val OnSurface: Color
    val SurfaceVariant: Color
    val OnSurfaceVariant: Color
    val SurfaceTint: Color
    val InverseSurface: Color
    val InverseOnSurface: Color
    val Error : Color
    val OnError : Color
    val ErrorContainer: Color
    val OnErrorContainer: Color
    val Outline : Color
    val OutlineVariant: Color
    val scrim : Color
    val SurfaceBright: Color
    val SurfaceContainer: Color
    val SurfaceContainerHigh: Color
    val SurfaceContainerHighest: Color
    val SurfaceContainerLow: Color
    val SurfaceContainerLowest: Color
    val SurfaceDim: Color
}