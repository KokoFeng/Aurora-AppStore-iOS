/*
 * SPDX-FileCopyrightText: 2026 Aurora OSS
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package com.aurora.store.compose.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Satu item pada bar bawah kaca.
 * @param iconRes Ikon tab
 * @param label Nama tab
 * @param badge Angka lencana (0 = tidak tampil)
 */
data class GlassTab(
    @DrawableRes val iconRes: Int,
    val label: String,
    val badge: Int = 0
)

/**
 * Bar navigasi bawah mengambang ala App Store iOS: kapsul kaca berisi tab,
 * ditambah tombol pencarian bulat terpisah di sebelah kanan.
 */
@Composable
fun GlassBottomBar(
    tabs: List<GlassTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    onSearchClick: () -> Unit,
    @DrawableRes searchIconRes: Int,
    modifier: Modifier = Modifier
) {
    val scheme = MaterialTheme.colorScheme
    val glass = scheme.surface.copy(alpha = 0.88f)
    val border = BorderStroke(0.6.dp, scheme.outlineVariant.copy(alpha = 0.7f))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .height(64.dp),
            shape = RoundedCornerShape(32.dp),
            color = glass,
            shadowElevation = 12.dp,
            border = border
        ) {
            Row(modifier = Modifier.padding(5.dp)) {
                tabs.forEachIndexed { index, tab ->
                    GlassTabItem(
                        tab = tab,
                        selected = index == selectedIndex,
                        onClick = { onTabSelected(index) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Surface(
            onClick = onSearchClick,
            modifier = Modifier.size(64.dp),
            shape = CircleShape,
            color = glass,
            shadowElevation = 12.dp,
            border = border
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(searchIconRes),
                    contentDescription = null,
                    tint = scheme.primary,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Composable
private fun GlassTabItem(
    tab: GlassTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scheme = MaterialTheme.colorScheme
    val tint = if (selected) scheme.primary else scheme.onSurface
    val highlight = if (selected) scheme.onSurface.copy(alpha = 0.09f) else Color.Transparent

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(27.dp))
            .background(highlight)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (tab.badge > 0) {
                BadgedBox(badge = { Badge { Text("${tab.badge}") } }) {
                    Icon(
                        painter = painterResource(tab.iconRes),
                        contentDescription = null,
                        tint = tint,
                        modifier = Modifier.size(24.dp)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(tab.iconRes),
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = tab.label,
                color = tint,
                fontSize = 11.sp,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}

/**
 * Header judul besar ala App Store iOS, dengan tombol unduhan dan avatar akun di kanan.
 */
@Composable
fun LargeTitleHeader(
    title: String,
    @DrawableRes downloadIconRes: Int,
    @DrawableRes accountIconRes: Int,
    onDownloadsClick: () -> Unit,
    onAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scheme = MaterialTheme.colorScheme
    Row(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(start = 20.dp, end = 16.dp, top = 8.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = scheme.onBackground,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        IconButton(onClick = onDownloadsClick) {
            Icon(
                painter = painterResource(downloadIconRes),
                contentDescription = null,
                tint = scheme.primary
            )
        }
        Surface(
            onClick = onAccountClick,
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            color = scheme.primary
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(accountIconRes),
                    contentDescription = null,
                    tint = scheme.onPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}
