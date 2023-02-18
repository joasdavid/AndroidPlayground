package pt.joasvpereira.sessionfeature.compose.select.session

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.PlusSolid
import pt.joasvpereira.sessionfeature.R
import pt.joasvpereira.core.domain.data.SessionItem

@Composable
internal fun SelectSessionScreen(
    sessionItems: List<SessionItem>?,
    isEditMode: Boolean = false,
    onSessionSelected: (SessionItem) -> Unit,
    onProfileClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        SimpleSpace(100.dp)
        Text("Please Select your profile:", style = MaterialTheme.typography.titleLarge)
        SimpleSpace(60.dp)
        sessionItems?.let {
            SessionsContent(it, onSessionSelected = onSessionSelected, onCreateNewSession = onProfileClicked, isEditMode = isEditMode)
        }
    }
}

@Composable
private fun SessionsContent(
    sessionItems: List<SessionItem>,
    isEditMode: Boolean = false,
    onSessionSelected: (SessionItem) -> Unit,
    onCreateNewSession: () -> Unit
) {
    if (sessionItems.isEmpty()) {
        SessionsContentEmpty(onCreateNewSession)
    } else {
        SessionsContentList(sessionItems, isEditMode, onSessionSelected, onCreateNewSession)
    }
}

private sealed class SessionsContentItemTypes {
    data class SessionType(val item: SessionItem) : SessionsContentItemTypes()
    object CreateType : SessionsContentItemTypes()
}


@Composable
private fun SessionsContentList(
    sessionItems: List<SessionItem>,
    isEditMode: Boolean = false,
    onSessionSelected: (SessionItem) -> Unit,
    onCreateNewSession: () -> Unit,
) {
    val workingList = ArrayList<SessionsContentItemTypes>()
        .apply {
            sessionItems.forEach {
                add(SessionsContentItemTypes.SessionType(it))
            }
            add(SessionsContentItemTypes.CreateType)
        }
        .withIndex()
        .groupBy {
            it.index / 2
        }
        .map {
            val isLast = it.value.size == 1
            Pair(
                it.value[0].value,
                if (isLast) null else it.value[1].value
            )
        }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(workingList) {
            ContentListLine(pair = it, isEditMode, onSessionSelected = onSessionSelected, onCreateNewSession = onCreateNewSession)
        }
    }
}

@Composable
private fun ContentListLine(
    pair: Pair<SessionsContentItemTypes, SessionsContentItemTypes?>,
    isEditMode: Boolean = false,
    onSessionSelected: (SessionItem) -> Unit,
    onCreateNewSession: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        pair.toList().forEach {
            when (it) {
                SessionsContentItemTypes.CreateType -> {
                    if (!isEditMode) {
                        ContentListLineItemCreateSession(onCreateNewSession)
                    } else {
                        SimpleSpace(size = 60.dp)
                    }
                }

                is SessionsContentItemTypes.SessionType -> ContentListLineItemSession(
                    sessionItem = it.item,
                    isEditMode = isEditMode,
                    onSessionSelected = onSessionSelected
                )

                null -> SimpleSpace(size = 60.dp)
            }
        }
    }
}

@Composable
private fun ContentListLineItemSession(
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
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        modifier = modifier.rotate(angle)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ItemContainer(
            modifier = modifier,
            onClick = {
                onSessionSelected(sessionItem)
            }
        ) {
            if (sessionItem.image != null) {
                Image(
                    bitmap = sessionItem.image!!.asImageBitmap(),
                    contentDescription = "",
                    Modifier.size(60.dp),
                    contentScale = ContentScale.FillBounds
                )
            } else {
                Image(
                    painterResource(id = R.drawable.usericon),
                    contentDescription = "",
                    Modifier
                        .size(60.dp)
                        .padding(10.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        SimpleSpace(size = 2.dp)
        Text(
            text = sessionItem.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(80.dp)
        )
    }
}

@Composable
private fun ContentListLineItemCreateSession(
    onCreateNewSession: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CreateProfileButton {
            onCreateNewSession()
        }
    }
}

@Composable
private fun SessionsContentEmpty(onCreateNewSession: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateProfileButton(onCreateNewSession)
        SimpleSpace(size = 48.dp)
        Text(
            text = "No profile is yet created\nplese tap on the button to create the first profile",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun CreateProfileButton(onCreateNewSession: () -> Unit) {
    ItemContainer(onClick = { onCreateNewSession() }) {
        Icon(
            imageVector = LineAwesomeIcons.PlusSolid,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
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
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        content()
    }
}

@Preview
@Composable
private fun SelectSessionScreenPreview() {
    SelectSessionScreen(sessionItems = listOf(
        SessionItem(
            id = 0,
            image = null,
            name = "session 1"
        ),
        SessionItem(
            id = 0,
            image = null,
            name = "Casa"
        ),
        SessionItem(
            id = 0,
            image = null,
            name = "Garagem"
        ),
        SessionItem(
            id = 0,
            image = null,
            name = "Garagem"
        ),
    ), onSessionSelected = {}, onProfileClicked = {})
}

@Preview
@Composable
private fun SelectSessionScreenEmptyPreview() {
    SelectSessionScreen(sessionItems = listOf(), onSessionSelected = {}, onProfileClicked = {})
}

@Preview
@Composable
private fun SelectSessionScreenNoListPreview() {
    SelectSessionScreen(sessionItems = null, onSessionSelected = {}, onProfileClicked = {})
}