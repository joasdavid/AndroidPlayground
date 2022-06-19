package pt.joasvpereira.xorganizer.presentation.compose.folder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.xorganizer.presentation.compose.common.holder.item.ItemHolder
import pt.joasvpereira.xorganizer.presentation.compose.common.holder.search.SearchHolder
import pt.joasvpereira.xorganizer.presentation.compose.division.SingleItem
import pt.joasvpereira.xorganizer.presentation.compose.navigation.ScreenNavigation
import pt.joasvpereira.xorganizer.presentation.theme.DynamicTheme

val fakeList = mutableListOf(
    SingleItem(
        title = "Carregador",
        description = null,
        tags = listOf("tipe c"),
        isUsed = false
    ),
    SingleItem(
        title = "Carregador",
        description = null,
        tags = listOf("tipe c"),
        isUsed = false
    ),
    SingleItem(
        title = "cabo c to usb",
        description = null,
        tags = listOf("tipe c", "cabo", "usb"),
        isUsed = false
    ),
)

@Composable
fun FolderScreen(
    id: Int,
    navController: NavController? = null
) {
    DynamicTheme {
        FolderScreenContent(
            folderName = "Box 1",
            onBackClick = { navController?.popBackStack() },
            items = fakeList,
            onItemClick = {navController?.navigate(ScreenNavigation.ItemScreen.createNavigationRoute(it))}
        )
    }
}

@Composable
private fun FolderScreenContent(
    folderName: String,
    items: List<SingleItem>,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer) {
        Column {
            ToolBar(title = folderName, onBackClick = onBackClick)
            Body(items = items, onItemClick = onItemClick)
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
private fun Body(items: List<SingleItem>, onItemClick: (Int) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp
                )
            )
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 16.dp)) {
            item {
                SearchHolder(onSearchClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(16.dp))
            }
            item { SimpleSpace(size = 10.dp) }
            items(items.size) { index: Int ->
                ItemHolder(onItemClick = { onItemClick(index) }, item = items[index])
            }
        }
    }
}

@Preview()
@Composable
fun ToolBarPreview() {
    FolderScreenContent(
        folderName = "Box 1",
        onBackClick = {},
        items = fakeList,
        onItemClick = {},
    )
}
