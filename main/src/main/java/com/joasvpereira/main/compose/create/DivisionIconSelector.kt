package com.joasvpereira.main.compose.create

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.main.presentation.icons.DivisionIcon
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

private data class DivisionIconSelectorState(
    val currentIconPos: Int,
    val direction: DivisionIconSelectorDirection = DivisionIconSelectorDirection.NONE,
)

private enum class DivisionIconSelectorDirection {
    RIGHT,
    LEFT,
    NONE,
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DivisionIconSelector(
    modifier: Modifier = Modifier,
    defaultPos: Int = 0,
    onIconSelected: (DivisionIcon) -> Unit,
) {
    var selectedPosition by remember(defaultPos) {
        mutableStateOf(DivisionIconSelectorState(currentIconPos = defaultPos))
    }

    val shape = CircleShape
    Row(
        Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = shape,
            )
            .padding(vertical = 10.dp)
            .padding(horizontal = 13.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    selectedPosition = if (selectedPosition.currentIconPos == 0) {
                        DivisionIconSelectorState(DivisionIcons.all.size - 1, DivisionIconSelectorDirection.LEFT)
                    } else {
                        DivisionIconSelectorState((selectedPosition.currentIconPos - 1), DivisionIconSelectorDirection.LEFT)
                    }
                    onIconSelected(DivisionIcons.all[selectedPosition.currentIconPos])
                },
        )

        Box(
            Modifier
                .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            AnimatedContent(
                targetState = selectedPosition,
                transitionSpec = {
                    when (selectedPosition.direction) {
                        DivisionIconSelectorDirection.RIGHT -> {
                            slideIntoContainer(
                                towards = AnimatedContentScope.SlideDirection.Left,
                                animationSpec = tween(1000),
                            ) + fadeIn() with slideOutOfContainer(
                                towards = AnimatedContentScope.SlideDirection.Left,
                                animationSpec = tween(1000),
                            ) + fadeOut(animationSpec = tween(1000))
                        }

                        DivisionIconSelectorDirection.LEFT -> {
                            slideIntoContainer(
                                towards = AnimatedContentScope.SlideDirection.Right,
                                animationSpec = tween(1000),
                            ) + fadeIn() with slideOutOfContainer(
                                towards = AnimatedContentScope.SlideDirection.Right,
                                animationSpec = tween(1000),
                            ) + fadeOut(animationSpec = tween(1000))
                        }

                        DivisionIconSelectorDirection.NONE -> fadeIn() with fadeOut()
                    }
                },
            ) {
                val currentIconData = DivisionIcons.all[it.currentIconPos]
                Icon(
                    painter = painterResource(id = currentIconData.resId),
                    contentDescription = currentIconData.name,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(50.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    selectedPosition = if (selectedPosition.currentIconPos == DivisionIcons.all.size - 1) {
                        DivisionIconSelectorState(0, DivisionIconSelectorDirection.RIGHT)
                    } else {
                        DivisionIconSelectorState((selectedPosition.currentIconPos + 1), DivisionIconSelectorDirection.RIGHT)
                    }
                    onIconSelected(DivisionIcons.all[selectedPosition.currentIconPos])
                },
        )
    }
}

@Composable
fun DivisionIconSelector(
    modifier: Modifier = Modifier,
    iconSelected: DivisionIcon = DivisionIcons.all.first(),
    onIconSelected: (DivisionIcon) -> Unit,
) {
    val pos = DivisionIcons.all.indexOf(iconSelected)
    DivisionIconSelector(modifier = modifier, defaultPos = pos, onIconSelected = onIconSelected)
}

@UiModePreview
@Composable
private fun DivisionIconSelectorPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        DivisionIconSelector(modifier = Modifier, defaultPos = 0, onIconSelected = {})
    }
}
