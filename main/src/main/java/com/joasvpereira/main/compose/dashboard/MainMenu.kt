package com.joasvpereira.main.compose.dashboard

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.joasvpereira.lib.compose.spacer.HorizontalSpace
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.lib.compose.spacer.VerticalSpace
import pt.joasvpereira.coreui.preview.PreviewWrapperWithTheme
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.session.SessionIconHolder
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun MainMenuCompact(
    sessionName: String,
    sessionImage: Bitmap?,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
    isCompact: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Slots(
        header = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SessionIconHolder(
                        sessionName = sessionName,
                        sessionImage = sessionImage,
                        modifier = Modifier.size(70.dp),
                    )

                    if (!isCompact) {
                        HorizontalSpace(width = 10.dp)
                        Column {
                            Text(
                                text = "Welcome:",
                                style = MaterialTheme.typography.labelSmall,
                            )
                            Text(text = sessionName, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
                VerticalSpace(height = 10.dp)
            }
        },
        body = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SimpleSpace(size = 0.dp)
                TextButton(
                    onClick = onLogout,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.background),
                ) {
                    if (!isCompact) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
                        HorizontalSpace(width = 5.dp)
                        Text(text = "Settings")
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp),
                        )
                    }
                }
            }
        },
        footer = {
            Text(
                text = "v1.0.0",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
            )
        },
        modifier = modifier.background(MaterialTheme.colorScheme.inversePrimary),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .90f),
        contentColor = MaterialTheme.colorScheme.background,
        contentPadding = contentPadding,
    )
}

@Composable
private fun Slots(
    header: @Composable @UiComposable BoxScope.() -> Unit,
    body: @Composable BoxScope.() -> Unit,
    footer: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        ConstraintLayout(
            modifier
                .background(color = color)
                .padding(contentPadding),
        ) {
            val (
                headerRef,
                bodyRef,
                footerRef,
                divHeaderBody,
                divBodyFooter,
            ) = createRefs()
            Box(
                modifier = Modifier
                    .constrainAs(headerRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    },
                content = header,
            )

            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .constrainAs(divHeaderBody) {
                        top.linkTo(headerRef.bottom, 5.dp)
                        start.linkTo(parent.start, 5.dp)
                        end.linkTo(parent.end, 5.dp)
                        width = Dimension.fillToConstraints
                    },
            )

            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .background(color = MaterialTheme.colorScheme.outline, shape = CircleShape)
                    .constrainAs(divBodyFooter) {
                        bottom.linkTo(footerRef.top, 5.dp)
                        start.linkTo(parent.start, 5.dp)
                        end.linkTo(parent.end, 5.dp)
                        width = Dimension.fillToConstraints
                    },
            )

            Box(
                modifier = Modifier
                    .constrainAs(bodyRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(divHeaderBody.bottom)
                        bottom.linkTo(divBodyFooter.top)
                        height = Dimension.preferredWrapContent
                    },
            ) { body() }
            Box(
                modifier = Modifier
                    .constrainAs(footerRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
            ) { footer() }
        }
    }
}

@Composable
fun SmallTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(vertical = 0.dp, horizontal = 16.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) =
    CompositionLocalProvider(
        LocalTextStyle provides MaterialTheme.typography.labelSmall,
    ) {
        Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = colors,
            elevation = elevation,
            border = border,
            contentPadding = contentPadding,
            interactionSource = interactionSource,
            content = content,
        )
    }

@UiModePreview
@Composable
fun MainMenuCompactPreview() {
    PreviewWrapperWithTheme(ThemeOption.THEME_DEFAULT, isDynamicColor = false) {
        MainMenuCompact(
            sessionName = "Test",
            sessionImage = null,
            isCompact = false,
            modifier = Modifier
                .height(800.dp)
                .width(IntrinsicSize.Max),
            onLogout = {},
        )
    }
}

@UiModePreview
@Composable
fun SlotsPreview() {
    PreviewWrapperWithTheme(ThemeOption.THEME_DEFAULT, isDynamicColor = false) {
        Slots(
            header = {
                Spacer(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Cyan),
                )
            },
            body = {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                )
            },
            footer = {
                Spacer(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Green),
                )
            },
            modifier = Modifier.width(IntrinsicSize.Max),
        )
    }
}
