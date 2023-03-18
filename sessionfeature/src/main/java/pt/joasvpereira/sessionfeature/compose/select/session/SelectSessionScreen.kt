package pt.joasvpereira.sessionfeature.compose.select.session

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.lib.compose.spacer.VerticalSpace
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.PlusSolid
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.coreui.adaptative.AdaptableHeaderSpace
import pt.joasvpereira.coreui.preview.FoldablePreview
import pt.joasvpereira.coreui.preview.LargePreview
import pt.joasvpereira.coreui.preview.PreviewWrapper
import pt.joasvpereira.coreui.session.SessionIconHolder
import pt.joasvpereira.sessionfeature.R

@Composable
internal fun SelectSessionScreen(
    sessionItems: List<SessionItem>?,
    isEditMode: Boolean = false,
    numberOfColumns: Int = SelectSessionScreenDefaults.numberOfColumns(),
    isExpanded: Boolean = SelectSessionScreenDefaults.expandedByScreenSize(),
    onSessionSelected: (SessionItem) -> Unit,
    onCreateNewSession: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        AdaptableHeaderSpace()
        Text(
            text = stringResource(R.string.please_select_your_profile),
            style = MaterialTheme.typography.titleLarge,
        )
        SimpleSpace(60.dp)

        sessionItems?.run {
            LazyVerticalGrid(
                columns = GridCells.Fixed(numberOfColumns),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                if (isEmpty()) {
                    item(span = { GridItemSpan(numberOfColumns) }) {
                        SessionsContentEmpty(onCreateNewSession)
                    }
                } else {
                    items(size) { index ->
                        ClickableSessionItem(
                            sessionItem = get(index),
                            isEditMode = isEditMode,
                            onSessionSelected = onSessionSelected,
                            isExpanded = isExpanded,
                        )
                    }

                    item {
                        AnimatedVisibility(
                            visible = !isEditMode,
                            enter = fadeIn(),
                            exit = fadeOut(),
                        ) {
                            CreateProfileButton(onCreateNewSession = onCreateNewSession, isExpanded = isExpanded)
                        }
                    }
                }

                item {
                    VerticalSpace(height = 40.dp)
                }
            }
        }
    }
}

private const val ANIM_DURATION = 1000

@Composable
private fun ClickableSessionItem(
    sessionItem: SessionItem,
    isEditMode: Boolean = false,
    onSessionSelected: (SessionItem) -> Unit,
    isExpanded: Boolean = false,
) = if (isExpanded) {
    ClickableSessionItemExpanded(sessionItem = sessionItem, isEditMode, onSessionSelected)
} else {
    ClickableSessionItemCompact(sessionItem = sessionItem, isEditMode, onSessionSelected)
}

@Composable
private fun ClickableSessionItemCompact(
    sessionItem: SessionItem,
    isEditMode: Boolean = false,
    onSessionSelected: (SessionItem) -> Unit,
) {
    var modifier: Modifier = Modifier
    if (isEditMode) {
        val infiniteTransition = rememberInfiniteTransition()
        val angle by infiniteTransition.animateFloat(
            initialValue = -25F,
            targetValue = 25F,
            animationSpec = infiniteRepeatable(
                animation = tween(ANIM_DURATION, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        )
        modifier = modifier.rotate(angle)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ItemContainer(
            modifier = modifier,
            onClick = {
                onSessionSelected(sessionItem)
            },
        ) {
            SessionIconHolder(
                sessionName = sessionItem.name,
                sessionImage = sessionItem.image,
            )
        }
        SimpleSpace(size = 2.dp)
        Text(
            text = sessionItem.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(80.dp),
        )
    }
}

@Composable
private fun ClickableSessionItemExpanded(
    sessionItem: SessionItem,
    isEditMode: Boolean = false,
    onSessionSelected: (SessionItem) -> Unit,
) {
    var modifier: Modifier = Modifier
    if (isEditMode) {
        val infiniteTransition = rememberInfiniteTransition()
        val angle by infiniteTransition.animateFloat(
            initialValue = -25F,
            targetValue = 25F,
            animationSpec = infiniteRepeatable(
                animation = tween(ANIM_DURATION, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        )
        modifier = modifier.rotate(angle)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .expandedBorderWithClick {
                onSessionSelected(sessionItem)
            },
    ) {
        Box(modifier) {
            SessionIconHolder(
                sessionName = sessionItem.name,
                sessionImage = sessionItem.image,
            )
        }
        SimpleSpace(size = 20.dp)
        Text(
            text = sessionItem.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private const val PERCENT_30 = 30

@SuppressLint("ComposableModifierFactory")
@Composable
private fun Modifier.expandedBorderWithClick(clickAction: () -> Unit): Modifier {
    val outShape = RoundedCornerShape(PERCENT_30)
    return this
        .clip(outShape)
        .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            shape = outShape,
        )
        .clickable { clickAction() }
        .padding(10.dp)
}

@Composable
private fun SessionsContentEmpty(onCreateNewSession: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CreateProfileButton(onCreateNewSession = onCreateNewSession)
        SimpleSpace(size = 48.dp)
        Text(
            text = stringResource(R.string.create_profile_info),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
private fun CreateProfileButton(modifier: Modifier = Modifier, isExpanded: Boolean = false, onCreateNewSession: () -> Unit) {
    if (isExpanded) {
        CreateProfileButtonExpanded(modifier, onCreateNewSession)
    } else {
        CreateProfileButtonCompact(modifier, onCreateNewSession)
    }
}

@Composable
fun CreateProfileButtonCompact(modifier: Modifier, onCreateNewSession: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ItemContainer(
            onClick = { onCreateNewSession() },
        ) {
            Icon(
                imageVector = LineAwesomeIcons.PlusSolid,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

@Composable
fun CreateProfileButtonExpanded(modifier: Modifier, onCreateNewSession: () -> Unit) {
    Row(
        modifier = modifier.expandedBorderWithClick { onCreateNewSession() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ItemContainer(
            sizeDp = 40.dp,
            onClick = null,
        ) {
            Icon(
                imageVector = LineAwesomeIcons.PlusSolid,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
        SimpleSpace(size = 20.dp)
        Text(
            text = stringResource(R.string.create_a_new_profile),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun ItemContainer(modifier: Modifier = Modifier, sizeDp: Dp = 60.dp, onClick: (() -> Unit)?, content: @Composable () -> Unit) {
    Surface(
        Modifier
            .size(sizeDp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable {
                onClick?.invoke()
            }
            .then(modifier),
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        content()
    }
}

@Preview
@LargePreview
@FoldablePreview
@Composable
private fun SelectSessionScreenPreview() {
    PreviewWrapper {
        SelectSessionScreen(
            sessionItems = listOf(
                SessionItem(
                    id = 0,
                    image = null,
                    name = "session 1",
                ),
                SessionItem(
                    id = 0,
                    image = null,
                    name = "Casa",
                ),
                SessionItem(
                    id = 0,
                    image = null,
                    name = "Garagem",
                ),
                SessionItem(
                    id = 0,
                    image = null,
                    name = "Garagem",
                ),
            ),
            onSessionSelected = {},
            onCreateNewSession = {},
        )
    }
}

@Preview
@LargePreview
@FoldablePreview
@Composable
private fun SelectSessionScreenEmptyPreview() {
    PreviewWrapper {
        SelectSessionScreen(sessionItems = listOf(), onSessionSelected = {}, onCreateNewSession = {})
    }
}

@Preview
@LargePreview
@FoldablePreview
@Composable
private fun SelectSessionScreenNoListPreview() {
    PreviewWrapper {
        SelectSessionScreen(sessionItems = null, onSessionSelected = {}, onCreateNewSession = {})
    }
}

@Preview(group = "sessionItem")
@LargePreview
@FoldablePreview
@Composable
private fun ContentListLineItemSessionPreview() {
    PreviewWrapper {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ClickableSessionItem(
                sessionItem = SessionItem(id = 0, name = "Test", image = null),
                isEditMode = false,
                onSessionSelected = {},
                isExpanded = false,
            )
            ClickableSessionItem(
                sessionItem = SessionItem(id = 0, name = "Test", image = null),
                isEditMode = false,
                onSessionSelected = {},
                isExpanded = true,
            )
        }
    }
}
