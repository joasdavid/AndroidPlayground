package pt.joasvpereira.xorganizer.presentation.compose.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.joasvpereira.xorganizer.presentation.compose.common.shield.NameShield
import pt.joasvpereira.xorganizer.presentation.compose.division.SingleItem
import pt.joasvpereira.xorganizer.presentation.theme.DynamicTheme

enum class Mode {
    EDIT,
    READ,
    NOT_SET
}

data class ItemScreenUiState(val mode: Mode, val loading: Boolean, val item: SingleItem? = null)

class ItemScreenViewModel(private val id: Int = 0, private val mode: Mode = Mode.READ) : ViewModel() {
    var uiState by mutableStateOf(
        ItemScreenUiState(
            mode = mode,
            loading = true
        )
    )
        private set

    init {
        viewModelScope.launch {
            delay(1000L)
            uiState = uiState.copy(
                loading = false, item = SingleItem(
                    title = "Xpto",
                    description = "This is a Xpto Item",
                    tags = listOf(),
                    isUsed = true
                )
            )
        }
    }
}

@Composable
fun ItemScreen(
    navController: NavController? = null,
    viewModel: ItemScreenViewModel
) {
    DynamicTheme {
        ItemScreenContent(
            title = "",
            data = viewModel.uiState,
            onBackClick = { navController?.popBackStack() }
        )
    }
}

@Composable
fun ItemScreenContent(
    title: String,
    data: ItemScreenUiState,
    onBackClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        50.dp
                    )
                )
        ) {
            Column {
                ToolBar(title = "" , onBackClick = onBackClick)
                if (data.item != null)
                    ListOfContent(data.item, data.mode)
                if (data.loading) {
                    Loading()
                }
            }
        }
    }
}

@Composable
private fun ToolBar(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(bottom = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onBackClick() }
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null
        )
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ListOfContent(singleItem: SingleItem, mode: Mode) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { SimpleSpace(size = 20.dp) }

        item {
            NameShield(
                text = singleItem.title,
                modifier = Modifier.size(85.dp),
                textStyle = TextStyle.Default.copy(fontSize = 50.sp)
            )
        }

        item {
            Text(text = "Name:", fontWeight = FontWeight.Bold)
        }

        item {
            Text(text = singleItem.title)
        }

        item {
            Text(text = "description:", fontWeight = FontWeight.Bold)
        }

        item {
            Text(text = singleItem.description ?: "-")
        }

        item {
            Text(text = "Status:", fontWeight = FontWeight.Bold)
        }

        item {
            Text(text = if (singleItem.isUsed) "USED" else "STORED")
        }
    }
}

@Preview()
@Composable
fun ItemScreenContentPreview() {
    ItemScreenContent(
        title = "Test_title",
        data = ItemScreenUiState(mode = Mode.NOT_SET, loading = false, item = null),
        onBackClick = {}
    )
}