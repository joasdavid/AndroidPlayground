package pt.joasvpereira.sessionfeature.compose.select.session

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.PlusSolid
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.coreui.preview.FoldablePreview
import pt.joasvpereira.coreui.preview.LargePreview
import pt.joasvpereira.coreui.session.SessionIconHolder
import pt.joasvpereira.sessionfeature.R

@Composable
internal fun SelectSessionScreen(
    sessionItems: List<SessionItem>?,
    isEditMode: Boolean = false,
    onSessionSelected: (SessionItem) -> Unit,
    onCreateNewSession: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        SimpleSpace(100.dp)
        Text(
            text = stringResource(R.string.please_select_your_profile),
            style = MaterialTheme.typography.titleLarge,
        )
        SimpleSpace(60.dp)

        sessionItems?.run {
            LazyVerticalGrid(
                columns = GridCells.Fixed(numberOfColumns()),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                if (isEmpty()) {
                    item(span = { GridItemSpan(numberOfColumns()) }) {
                        SessionsContentEmpty(onCreateNewSession)
                    }
                } else {
                    items(size) { index ->
                        ClickableSessionItem(
                            sessionItem = get(index),
                            isEditMode = isEditMode,
                            onSessionSelected = onSessionSelected,
                        )
                    }

                    item {
                        CreateProfileButton(onCreateNewSession = onCreateNewSession)
                    }
                }
            }
        }
    }
}

const val ANIM_DURATION = 1000

@Composable
private fun ClickableSessionItem(
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
private fun CreateProfileButton(modifier: Modifier = Modifier, onCreateNewSession: () -> Unit) {
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
private fun ItemContainer(modifier: Modifier = Modifier, onClick: () -> Unit, content: @Composable () -> Unit) {
    Surface(
        Modifier
            .size(60.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable {
                onClick()
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

@Preview
@LargePreview
@FoldablePreview
@Composable
private fun SelectSessionScreenEmptyPreview() {
    SelectSessionScreen(sessionItems = listOf(), onSessionSelected = {}, onCreateNewSession = {})
}

@Preview
@LargePreview
@FoldablePreview
@Composable
private fun SelectSessionScreenNoListPreview() {
    SelectSessionScreen(sessionItems = null, onSessionSelected = {}, onCreateNewSession = {})
}

@Preview
@LargePreview
@FoldablePreview
@Composable
private fun ContentListLineItemSessionPreview() {
    ClickableSessionItem(
        sessionItem = SessionItem(id = 0, name = "Test", image = null),
        isEditMode = false,
        onSessionSelected = {},
    )
}
