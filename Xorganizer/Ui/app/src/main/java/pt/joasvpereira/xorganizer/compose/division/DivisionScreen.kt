package pt.joasvpereira.xorganizer.compose.division

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Checkbox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.BedSolid
import compose.icons.lineawesomeicons.HomeSolid
import compose.icons.lineawesomeicons.TabletAltSolid
import pt.joasvpereira.xorganizer.compose.common.CircleChart
import pt.joasvpereira.xorganizer.compose.common.CircleChartItem
import pt.joasvpereira.xorganizer.compose.common.container.Folder
import pt.joasvpereira.xorganizer.compose.common.holder.item.ItemHolder
import pt.joasvpereira.xorganizer.compose.common.holder.search.SearchHolder
import pt.joasvpereira.xorganizer.compose.itemTest
import pt.joasvpereira.xorganizer.compose.navigation.ScreenNavigation
import pt.joasvpereira.xorganizer.ui.theme.DynamicTheme
import pt.joasvpereira.xorganizer.ui.theme.SystemUiOptions
import pt.joasvpereira.xorganizer.ui.theme.ThemeOption

class DivisionScreenViewModel : ViewModel() {

    private var _id: Int = -1
    private lateinit var _division: itemTest
    val division: itemTest
        get() = _division

    val percentageFolders: Float
        get() = (_division.boxCount + _division.childCount).toFloat().run {
            _division.boxCount / this
        }

    val boxes: List<SingleBox>
        get() = mutableListOf<SingleBox>().apply {
            for (i in 0 until division.boxCount) {
                add(
                    SingleBox(i, "Box $i", nrItems = i).run {
                        if (i % 2 == 0) {
                            this.copy(description = "description $i")
                        }
                        this
                    }
                )
            }
        }

    val items: List<SingleItem>
        get() = _items[_id]

    fun fetchDivision(id: Int) {
        _id = id
        _division = div[id]
    }

    private val _items
        get() = mutableListOf<List<SingleItem>>(
            mutableListOf(
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
            ),
            mutableListOf(
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
                    isUsed = true
                ),
                SingleItem(
                    title = "base wirelless",
                    description = null,
                    tags = listOf("tipe c", "preto", "MI", "10v", "setOf2", "plug in", "pixel 5"),
                    isUsed = true
                ),
            ),
        ).apply {
            val lastList = mutableListOf<SingleItem>()
            for (i in 0 until _division.childCount) {
                lastList.add(
                    SingleItem(
                        title = "random item $i"
                    ),
                )
            }
            add(lastList)
        }

    private val div = mutableListOf(
        itemTest(
            title = "Division 1",
            description = "Description 1",
            vectorImg = LineAwesomeIcons.HomeSolid,
            boxCount = 2,
            childCount = 3
        ),
        itemTest(
            title = "Division 2",
            description = "",
            vectorImg = LineAwesomeIcons.TabletAltSolid,
            boxCount = 1,
            childCount = 1,
            option = ThemeOption.THEME_BLUE
        ),
        itemTest(
            title = "Division 3",
            description = "Long discription asdasldkslad;askd;lsak;dasdasdl",
            vectorImg = LineAwesomeIcons.BedSolid,
            boxCount = 100,
            childCount = 40,
            option = ThemeOption.THEME_GREEN
        )
    )
}

@Composable
fun DivisionScreen(
    id: Int,
    viewModel: DivisionScreenViewModel = DivisionScreenViewModel(),
    navController: NavController? = null
) {
    viewModel.fetchDivision(id)
    val itemTest = viewModel.division
    DynamicTheme(
        option = itemTest.option,
        systemUiOptions = SystemUiOptions.OverrideSystemColor
    ) {
        DivisionContent(
            divisionName = itemTest.title,
            shieldImg = itemTest.vectorImg,
            dataBox = viewModel.boxes,
            dataItem = viewModel.items,
            onBackClick = { navController?.popBackStack() },
            percentageFolders = viewModel.percentageFolders,
            percentageItems = 1f,
            onBoxClick = { navController?.navigate(ScreenNavigation.FolderScreen.createNavigationRoute(it.id)) },
            onItemClick = { navController?.navigate(ScreenNavigation.ItemScreen.createNavigationRoute(it)) },
        )
    }
}

@Composable
fun DivisionContent(
    divisionName: String,
    shieldImg: ImageVector,
    percentageFolders: Float,
    percentageItems: Float,
    dataBox: List<SingleBox>,
    dataItem: List<SingleItem>,
    onBoxClick: (SingleBox) -> Unit,
    onItemClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer) {
        Column {
            DivisionDetailsHeader(
                divisionName = divisionName,
                shieldImg = shieldImg,
                nrFolders = dataBox.size,
                percentageFolders = percentageFolders,
                nrItems = dataItem.size,
                percentageItems = percentageItems,
                onBackClick = onBackClick,
                modifier = Modifier
            )
            DivisionContentBody(
                dataBox = dataBox,
                dataItem = dataItem,
                onBoxClick = onBoxClick,
                onItemClick = { onItemClick(it.id) })
        }
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Composable
fun DivisionDetailsHeader(
    divisionName: String,
    shieldImg: ImageVector,
    nrFolders: Int,
    percentageFolders: Float,
    nrItems: Int,
    percentageItems: Float,
    onBackClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(bottom = 4.dp)
    ) {
        DivisionToolBar(divisionName, onBackClick)
        SimpleSpace(size = 20.dp)
        DivisionChart(
            nrFolders,
            percentageFolders,
            nrItems,
            percentageItems,
            shieldImg,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun DivisionChart(
    nrFolders: Int,
    percentageFolders: Float,
    nrItems: Int,
    percentageItems: Float,
    shieldImg: ImageVector,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircleChart(
                items = buildChartItems(percentageFolders, percentageItems),
                backgroundColor = MaterialTheme.colorScheme.outline.copy(alpha = .5f),
                radius = 25.dp,
                strokeSize = 10.dp,
                innerPadding = 0.dp,
                delay = 1000
            )
            Icon(
                imageVector = shieldImg,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
        SimpleSpace(size = 20.dp)
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                )
                SimpleSpace(size = 5.dp)
                Text(text = "Folders: $nrFolders")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .background(MaterialTheme.colorScheme.tertiary, CircleShape)
                )
                SimpleSpace(size = 5.dp)
                Text(text = "Items: $nrItems")
            }
        }
    }
}

@Composable
private fun DivisionToolBar(
    divisionName: String,
    onBackClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onBackClick() }
        )

        Text(
            text = divisionName,
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
fun buildChartItems(
    percentageFolders: Float,
    percentageItems: Float,
): List<CircleChartItem> {
    return listOf(
        CircleChartItem(percentageFolders, MaterialTheme.colorScheme.primary),
        CircleChartItem(percentageItems, MaterialTheme.colorScheme.tertiary),
    )
}

data class SingleBox(
    val id: Int = -1,
    val title: String,
    val description: String? = null,
    val nrItems: Int = 0
)

data class SingleItem(
    val id: Int = -1,
    val title: String,
    val description: String? = null,
    val tags: List<String> = listOf(),
    val isUsed: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DivisionContentBody(
    dataBox: List<SingleBox>,
    onBoxClick: (SingleBox) -> Unit = {},
    dataItem: List<SingleItem>,
    onItemClick: (SingleItem) -> Unit = {},
) {
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
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(horizontal = 16.dp)) {
            item(span = { GridItemSpan(2) }) {
                SearchHolder(
                    onSearchClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(16.dp)
                )
            }
            items(dataBox.size) { index: Int ->
                FolderHolder(
                    data = dataBox[index],
                    onClick = onBoxClick
                )
            }
            items(dataItem.size, span = { GridItemSpan(2) }) { index: Int ->
                ItemHolder(
                    item = dataItem[index],
                    onItemClick = { onItemClick(dataItem[index]) }
                )
            }
            item(span = { GridItemSpan(2) }) {
                SimpleSpace(size = 60.dp)
            }
        }
    }
}

@Composable
fun FolderHolder(data: SingleBox, onClick: (SingleBox) -> Unit) {
    Folder(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp)
            .padding(4.dp)
            .clickable { onClick(data) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Text(text = data.title, modifier = Modifier.align(Alignment.Center))
        }
    }
}

//@Preview()
@Composable
fun DivisionDetailsHeaderPreview() {
    DynamicTheme(ThemeOption.THEME_BLUE) {
        DivisionDetailsHeader(
            divisionName = "Division Test",
            shieldImg = Icons.Default.Home,
            nrFolders = 3,
            percentageFolders = .75f,
            nrItems = 4,
            percentageItems = .25f,
            onBackClick = {},
            modifier = Modifier
        )
    }
}

//@Preview()
@Composable
fun DivisionScreenPreview(viewModel: DivisionScreenViewModel = DivisionScreenViewModel()) {
    DynamicTheme(ThemeOption.THEME_BLUE) {
        DivisionScreen(0)
    }
}

@Composable
fun FilterDialog() {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        buttons = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Save Options")
                }
            }
        },
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier.padding(16.dp),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) { Text(text = "Title //TODO//") }
        },
        text = {
            DynamicTheme(ThemeOption.THEME_GREEN) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SimpleSpace(size = 10.dp)
                    Text(text = "Included items:")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange = {})
                        SimpleSpace(size = 5.dp)
                        Text(text = "Folders")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange = {})
                        SimpleSpace(size = 5.dp)
                        Text(text = "Items")
                    }
                    SimpleSpace(size = 15.dp)
                    Text(text = "Included items:")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange = {})
                        SimpleSpace(size = 5.dp)
                        Text(text = "Unused")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange = {})
                        SimpleSpace(size = 5.dp)
                        Text(text = "Used")
                    }
                }
            }
        },
    )
}