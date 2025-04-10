package cd.wayupdotdev.ecodim.ui.theme

import androidx.compose.ui.graphics.Color

object EcodimColor {
    object Light : ColorSchemeType {
        override val Primary = Color(0xFFD3902A)
        override val OnPrimary = Color(0xFF402300)
        override val PrimaryContainer = Color(0xFFFEF2D0)
        override val OnPrimaryContainer = Color(0xFF5C3700)
        override val InversePrimary = Color(0xFF855400)

        override val Secondary = Color(0xFF9A7B3E)
        override val OnSecondary = Color(0xFF3C2A00)
        override val SecondaryContainer = Color(0xFFF9E4B8)
        override val OnSecondaryContainer = Color(0xFF4E3500)

        override val Tertiary = Color(0xFF6C5A3E)
        override val OnTertiary = Color(0xFF3F2D14)
        override val TertiaryContainer = Color(0xFFF0E1B3)
        override val OnTertiaryContainer = Color(0xFF4B3B20)

        override val Background = Color(0xFFFCF8F1)
        override val OnBackground = Color(0xFF3E2A18)

        override val Surface = Color(0xFFFCF8F1)
        override val OnSurface = Color(0xFF3E2A18)
        override val SurfaceVariant = Color(0xFFE5D1A4)
        override val OnSurfaceVariant = Color(0xFF4F4539)

        override val SurfaceTint = Primary
        override val InverseSurface = Color(0xFF3E2A18)
        override val InverseOnSurface = Color(0xFFFCF8F1)

        override val Error = Color(0xFFF2B8B5)
        override val OnError = Color(0xFF601410)
        override val ErrorContainer = Color(0xFF8C1D18)
        override val OnErrorContainer = Color(0xFFF9DEDC)

        override val Outline = Color(0xFF9D8E80)
        override val OutlineVariant = Color(0xFF4F4539)

        override val scrim = Color(0x66000000) // 40% black

        override val SurfaceBright = Color(0xFFF2E7D6)
        override val SurfaceDim = Color(0xFFDFD6C5)

        override val SurfaceContainerLowest = Color(0xFFF1E6D1)
        override val SurfaceContainerLow = Color(0xFFF1E0C5)
        override val SurfaceContainer = Color(0xFFF4E9D6)
        override val SurfaceContainerHigh = Color(0xFFF5F1E2)
        override val SurfaceContainerHighest = Color(0xFFFFFFFF)
    }

    object Dark : ColorSchemeType {
        override val Primary = Color(0xFFFFB951)
        override val OnPrimary = Color(0xFF402300)
        override val PrimaryContainer = Color(0xFF5C3700)
        override val OnPrimaryContainer = Color(0xFFFFDDAE)
        override val InversePrimary = Color(0xFF855400)

        override val Secondary = Color(0xFFE2BD83)
        override val OnSecondary = Color(0xFF432C05)
        override val SecondaryContainer = Color(0xFF5D4100)
        override val OnSecondaryContainer = Color(0xFFFFDEA8)

        override val Tertiary = Color(0xFFD0BBA4)
        override val OnTertiary = Color(0xFF3B2B17)
        override val TertiaryContainer = Color(0xFF53412C)
        override val OnTertiaryContainer = Color(0xFFF4DFC3)

        override val Background = Color(0xFF1C1B1F)
        override val OnBackground = Color(0xFFEAE0D9)

        override val Surface = Color(0xFF1C1B1F)
        override val OnSurface = Color(0xFFEAE0D9)
        override val SurfaceVariant = Color(0xFF4F4539)
        override val OnSurfaceVariant = Color(0xFFD2C3B3)

        override val SurfaceTint = Primary
        override val InverseSurface = Color(0xFFEAE0D9)
        override val InverseOnSurface = Color(0xFF1C1B1F)

        override val Error = Color(0xFFF2B8B5)
        override val OnError = Color(0xFF601410)
        override val ErrorContainer = Color(0xFF8C1D18)
        override val OnErrorContainer = Color(0xFFF9DEDC)

        override val Outline = Color(0xFF9D8E80)
        override val OutlineVariant = Color(0xFF4F4539)

        override val scrim = Color(0x66000000) // 40% black

        override val SurfaceBright = Color(0xFF2E2C30)
        override val SurfaceDim = Color(0xFF141316)

        override val SurfaceContainerLowest = Color(0xFF0F0D13)
        override val SurfaceContainerLow = Color(0xFF1D1B1E)
        override val SurfaceContainer = Color(0xFF211F22)
        override val SurfaceContainerHigh = Color(0xFF2B292D)
        override val SurfaceContainerHighest = Color(0xFF353337)
    }
}

val Color.Companion.Light: EcodimColor.Light
    get() = EcodimColor.Light

val Color.Companion.Dark: EcodimColor.Dark
    get() = EcodimColor.Dark