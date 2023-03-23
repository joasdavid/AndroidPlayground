package com.joasvpereira.main.compose.dashboard

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NewLabel
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.joasvpereira.lib.compose.spacer.HorizontalSpace
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.lib.compose.spacer.VerticalSpace
import pt.joasvpereira.coreui.adaptative.AdaptableHeaderSpace
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
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Slots(
        header = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                ,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    SessionIconHolder(
                        sessionName = sessionName,
                        sessionImage = sessionImage,
                        modifier = Modifier.size(70.dp)
                    )

                    HorizontalSpace(width = 10.dp)

                    Text(text = "Welcome:\n$sessionName")

                    HorizontalSpace(width = 10.dp)
                }
                VerticalSpace(height = 10.dp)
            }
        },
        body = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(50.dp),
                horizontalAlignment = Alignment.Start
            ) {
                SimpleSpace(size = 0.dp)
                Row(Modifier.clickable {
                    onLogout()
                }) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
                    HorizontalSpace(width = 5.dp)
                    Text(text = "Settings")
                }
                Row(Modifier.clickable {
                    onLogout()
                }) {
                    Icon(imageVector = Icons.Filled.NewLabel, contentDescription = "")
                    HorizontalSpace(width = 5.dp)
                    Text(text = "Place holde")
                }
                Row(Modifier.clickable {
                    onLogout()
                }) {
                    Icon(imageVector = Icons.Filled.NewLabel, contentDescription = "")
                    HorizontalSpace(width = 5.dp)
                    Text(text = "Place holde")
                }
            }
        },
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "App version v1.0.0".trimIndent(),
                    //textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        modifier = modifier,
        //color = MaterialTheme.colorScheme.background.copy(alpha = .85f),
        color = MaterialTheme.colorScheme.background,
        contentPadding = PaddingValues(
            start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 20.dp,
            end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
            top = contentPadding.calculateTopPadding(),
            bottom = contentPadding.calculateBottomPadding()
        )
    )
}

@Composable
private fun Slots(
    header: @Composable BoxScope.() -> Unit,
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
                .padding(contentPadding)) {
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
                        }
                ) { header() }

                Spacer(modifier = Modifier
                    .height(2.dp)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .constrainAs(divHeaderBody) {
                        top.linkTo(headerRef.bottom, 5.dp)
                        start.linkTo(parent.start, 5.dp)
                        end.linkTo(parent.end, 5.dp)
                        width = Dimension.fillToConstraints
                    }
                )

                Spacer(modifier = Modifier
                    .height(2.dp)
                    .background(color = MaterialTheme.colorScheme.outline, shape = CircleShape)
                    .constrainAs(divBodyFooter) {
                        bottom.linkTo(footerRef.top, 5.dp)
                        start.linkTo(parent.start, 5.dp)
                        end.linkTo(parent.end, 5.dp)
                        width = Dimension.fillToConstraints
                    }
                )

                Box(
                    modifier = Modifier
                        .constrainAs(bodyRef) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(divHeaderBody.bottom)
                            bottom.linkTo(divBodyFooter.top)
                            height = Dimension.fillToConstraints
                        }
                ) { body() }
                Box(
                    modifier = Modifier
                        .constrainAs(footerRef) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
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
    content: @Composable RowScope.() -> Unit
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
            content = content
        )
    }

@UiModePreview
@Composable
fun MainMenuCompactPreview() {
    PreviewWrapperWithTheme(ThemeOption.THEME_DEFAULT, isDynamicColor = false) {
        MainMenuCompact(
            sessionName = "Test",
            sessionImage = null,
            modifier = Modifier
                .height(800.dp)
                .width(294.75.dp),
            onLogout = {},
        )
    }
}