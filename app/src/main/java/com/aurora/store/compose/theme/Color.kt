/*
 * SPDX-FileCopyrightText: 2025 The Calyx Institute
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package com.aurora.store.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

/**
 * Whether the active [MaterialTheme] is dark.
 *
 * Derived from the resolved color scheme rather than [androidx.compose.foundation.isSystemInDarkTheme]
 * so it stays correct when the user forces a light/dark theme that differs from the system setting.
 */
@Composable
@ReadOnlyComposable
private fun isAppInDarkTheme(): Boolean = MaterialTheme.colorScheme.surface.luminance() < 0.5f

/**
 * Amber used to flag warnings/caveats. Lightened in dark theme for adequate contrast.
 */
val warningColor: Color
    @Composable @ReadOnlyComposable
    get() = if (isAppInDarkTheme()) Color(0xFFFFB74D) else Color(0xFFFF7600)

/**
 * Green used to flag positive/success states. Lightened in dark theme for adequate contrast.
 */
val successColor: Color
    @Composable @ReadOnlyComposable
    get() = if (isAppInDarkTheme()) Color(0xFF5BD27A) else Color(0xFF1B8738)

val colorGreen: Color
    @Composable @ReadOnlyComposable
    get() = if (isAppInDarkTheme()) Color(0xFF81C784) else Color(0xFF388E3C)

val colorRed: Color
    @Composable @ReadOnlyComposable
    get() = if (isAppInDarkTheme()) Color(0xFFE57373) else Color(0xFFD32F2F)

/**
 * Brand color schemes seeded from Aurora's accent (#6C63FF), used on devices that don't support
 * dynamic color (Android 11 and below) so the full palette stays on-brand instead of falling back
 * to Material's default purple baseline.
 */
val BrandLightColorScheme = lightColorScheme(
    primary = Color(0xFF007AFF),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD6E8FF),
    onPrimaryContainer = Color(0xFF002C5C),
    secondary = Color(0xFF8E8E93),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE9E9EF),
    onSecondaryContainer = Color(0xFF1C1C1E),
    tertiary = Color(0xFF34C759),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD8F5DF),
    onTertiaryContainer = Color(0xFF00210B),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFF2F2F7),
    onSurfaceVariant = Color(0xFF6D6D72),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF9F9FB),
    surfaceContainer = Color(0xFFF2F2F7),
    surfaceContainerHigh = Color(0xFFEBEBF0),
    surfaceContainerHighest = Color(0xFFE5E5EA),
    outline = Color(0xFFC7C7CC),
    outlineVariant = Color(0xFFE5E5EA),
    error = Color(0xFFFF3B30),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

val BrandDarkColorScheme = darkColorScheme(
    primary = Color(0xFF0A84FF),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF0A3A6E),
    onPrimaryContainer = Color(0xFFD6E8FF),
    secondary = Color(0xFF98989D),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF2C2C2E),
    onSecondaryContainer = Color(0xFFE5E5EA),
    tertiary = Color(0xFF30D158),
    onTertiary = Color(0xFF00210B),
    tertiaryContainer = Color(0xFF0B3D1B),
    onTertiaryContainer = Color(0xFFD8F5DF),
    background = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF000000),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF1C1C1E),
    onSurfaceVariant = Color(0xFF98989D),
    surfaceContainerLowest = Color(0xFF000000),
    surfaceContainerLow = Color(0xFF0C0C0E),
    surfaceContainer = Color(0xFF1C1C1E),
    surfaceContainerHigh = Color(0xFF2C2C2E),
    surfaceContainerHighest = Color(0xFF3A3A3C),
    outline = Color(0xFF48484A),
    outlineVariant = Color(0xFF2C2C2E),
    error = Color(0xFFFF453A),
    onError = Color(0xFF000000),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6)
)
