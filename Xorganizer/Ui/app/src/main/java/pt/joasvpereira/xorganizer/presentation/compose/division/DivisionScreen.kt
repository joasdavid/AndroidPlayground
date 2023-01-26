package pt.joasvpereira.xorganizer.presentation.compose.division

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Checkbox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
import pt.joasvpereira.coreui.scaffold.ToolbarTitleCentered
import pt.joasvpereira.xorganizer.domain.model.Box
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.model.StoredItem
import pt.joasvpereira.xorganizer.domain.usecase.box.IBoxesUseCase
import pt.joasvpereira.xorganizer.domain.usecase.box.SourceDivision
import pt.joasvpereira.xorganizer.domain.usecase.division.DivisionId
import pt.joasvpereira.xorganizer.domain.usecase.division.ISingleDivisionUseCase
import pt.joasvpereira.xorganizer.domain.usecase.item.IItemsUseCase
import pt.joasvpereira.xorganizer.presentation.compose.DivisionHolder
import pt.joasvpereira.xorganizer.presentation.compose.common.CircleChart
import pt.joasvpereira.xorganizer.presentation.compose.common.CircleChartItem
import pt.joasvpereira.xorganizer.presentation.compose.common.add.CreateFolderBottomSheet
import pt.joasvpereira.xorganizer.presentation.compose.common.add.CreateItemBottomSheet
import pt.joasvpereira.xorganizer.presentation.compose.common.container.Folder
import pt.joasvpereira.xorganizer.presentation.compose.common.holder.item.ItemHolder
import pt.joasvpereira.xorganizer.presentation.compose.common.holder.search.SearchHolder
import pt.joasvpereira.xorganizer.presentation.compose.navigation.ScreenNavigation
import pt.joasvpereira.xorganizer.presentation.icons.DivisionIcon
import pt.joasvpereira.xorganizer.presentation.icons.DivisionIcons
import pt.joasvpereira.xorganizer.presentation.mapper.BoxMapper
import pt.joasvpereira.xorganizer.presentation.mapper.DivisionsMapper
import pt.joasvpereira.xorganizer.presentation.mapper.ItemMapper
import pt.joasvpereira.xorganizer.presentation.mapper.mapToPresentationList

data class DivisionScreenUiState(
    val division: DivisionHolder = DivisionHolder(
        id = -1,
        title = "",
        description = "",
        imageName = "",
        boxCount = 0,
        childCount = 0
    ),
    val items: List<SingleItem> = emptyList(),
    val boxes: List<SingleBox> = emptyList(),
    val showAddOptions: Boolean = false,
    val isCreateFolderOpen: Boolean = false,
    val isCreateItemOpen: Boolean = false
)

class DivisionScreenViewModel(
    private val id: Int = 0,
    singleDivisionUseCase: ISingleDivisionUseCase,
    boxenUseCase: IBoxesUseCase,
    itemsUseCase: IItemsUseCase,
) : ViewModel() {

    val percentageFolders: Float by lazy {
        (uiState.division.boxCount + uiState.division.childCount).toFloat().run {
            uiState.division.boxCount / this
        }
    }

    var uiState by mutableStateOf(DivisionScreenUiState())

    init {
        viewModelScope.launch {
            singleDivisionUseCase.execute(DivisionId(id))?.run {
                val division = DivisionsMapper().mapToPresentation(this)

                uiState = uiState.copy(
                    division = division
                )

                val param = SourceDivision(division.id)


                launch {
                    /*boxenUseCase.execute(param).map {
                        BoxMapper().mapToPresentationList(it)
                    }.collect {
                        uiState = uiState.copy(
                            boxes = it
                        )
                    }*/
                }

                launch {
                   /* itemsUseCase.execute(param).map {
                        ItemMapper().mapToPresentationList(it)
                    }.collect {
                        uiState = uiState.copy(
                            items = it
                        )
                    }*/
                }
            }
        }
    }

    fun fabClick() {
        viewModelScope.launch {
            uiState = uiState.copy(showAddOptions = true)
            delay(20000)
            uiState = uiState.copy(showAddOptions = false)
        }
    }

    fun addFolderClick() {
        uiState = uiState.copy(
            showAddOptions = false,
            isCreateFolderOpen = true
        )
    }

    fun addItemClick() {
        uiState = uiState.copy(
            showAddOptions = false,
            isCreateItemOpen = true
        )
    }

    fun promptFolderClosed() {
        uiState = uiState.copy(isCreateFolderOpen = false)
    }

    fun promptItemClosed() {
        uiState = uiState.copy(isCreateItemOpen = false)
    }
}

@Composable
fun DivisionScreen(
    viewModel: DivisionScreenViewModel,
    navController: NavController? = null
) {
    DynamicTheme(
        option = viewModel.uiState.division.option
    ) {
        Box {
            DivisionContent(
                viewModel.uiState,
                onBackClick = { navController?.popBackStack() },
                onBoxClick = { navController?.navigate(ScreenNavigation.FolderScreen.createNavigationRoute(it.id)) },
                onItemClick = { navController?.navigate(ScreenNavigation.ItemScreen.createNavigationRoute(it)) },
                percentageFolders = viewModel.percentageFolders,
                onFabClick = { viewModel.fabClick() },
                onAddFolderClick = { viewModel.addFolderClick() },
                onAddItemClick = { viewModel.addItemClick() },
            )
            CreateFolderBottomSheet(
                isExpanded = viewModel.uiState.isCreateFolderOpen,
                onSaveClick = {
                    viewModel.promptFolderClosed()
                },
                onCloseClick = {
                    viewModel.promptFolderClosed()
                }
            )

            CreateItemBottomSheet(
                isExpanded = viewModel.uiState.isCreateItemOpen,
                onSaveClick = {
                    viewModel.promptItemClosed()
                },
                onCloseClick = {
                    viewModel.promptItemClosed()
                }
            )
        }
    }
}

@Composable
fun DivisionContent(
    uiState: DivisionScreenUiState,
    percentageFolders: Float,
    onBoxClick: (SingleBox) -> Unit,
    onItemClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onFabClick: () -> Unit,
    onAddFolderClick: () -> Unit,
    onAddItemClick: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer) {
        Column {
            DivisionDetailsHeader(
                divisionName = uiState.division.title,
                shieldImg = DivisionIcons.getBy(uiState.division.imageName,DivisionIcons.livingRoom).resId,
                nrFolders = uiState.boxes.size,
                percentageFolders = percentageFolders,
                nrItems = uiState.items.size,
                percentageItems = if (uiState.items.isEmpty()) 0f else 1f,
                onBackClick = onBackClick,
                modifier = Modifier
            )
            DivisionContentBody(
                dataBox = uiState.boxes,
                dataItem = uiState.items,
                onBoxClick = onBoxClick,
                onItemClick = { onItemClick(it.id) })
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(bottom = 5.dp)) {
            AnimatedVisibility(
                visible = !uiState.showAddOptions,
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                FloatingActionButton(
                    onClick = { onFabClick() }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
            AnimatedVisibility(
                visible = uiState.showAddOptions,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                Row {
                    FloatingActionButton(
                        onClick = { onAddFolderClick() },
                    ) {
                        Folder(
                            modifier = Modifier
                                .size(width = 21.dp, height = 16.dp)
                                .background(Color.Transparent),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add, contentDescription = null,
                                modifier = Modifier.size(8.dp)
                            )
                        }
                    }
                    SimpleSpace(size = 25.dp)
                    FloatingActionButton(
                        onClick = { onAddItemClick() },
                    ) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Composable
fun DivisionDetailsHeader(
    divisionName: String,
    shieldImg: Int,
    nrFolders: Int,
    percentageFolders: Float,
    nrItems: Int,
    percentageItems: Float,
    onBackClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        ToolbarTitleCentered(
            toolBarConfig = ToolBarConfig(title = divisionName,onBackClick = onBackClick),
        )
        SimpleSpace(size = 20.dp)
        DivisionChart(
            nrFolders,
            percentageFolders,
            nrItems,
            percentageItems,
            shieldImg,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        SimpleSpace(size = 20.dp)
    }
}

@Composable
fun DivisionChart(
    nrFolders: Int,
    percentageFolders: Float,
    nrItems: Int,
    percentageItems: Float,
    shieldImg: Int,
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
                painter = painterResource(id = shieldImg),
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
                SimpleSpace(size = 65.dp + WindowInsets.systemBars.asPaddingValues().calculateBottomPadding())
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
            shieldImg = DivisionIcons.desk.resId,
            nrFolders = 3,
            percentageFolders = .75f,
            nrItems = 4,
            percentageItems = .25f,
            onBackClick = {},
            modifier = Modifier
        )
    }
}
/*
@Preview()
@Composable
fun DivisionScreenPreview(
    viewModel: DivisionScreenViewModel = DivisionScreenViewModel(
        0,
        object : ISingleDivisionUseCase {
            override suspend fun execute(params: DivisionId) = Division(
                id = 0,
                name = "div",
                description = "",
                iconId = 0,
                nrBox = 0,
                nrItem = 0,
                themeId = 0
            )
        },
        object : IBoxesUseCase {
            override suspend fun execute(params: SourceDivision?) = emptyFlow<List<Box>>()
        },
        object : IItemsUseCase {
            override suspend fun execute(params: SourceDivision?) = emptyFlow<List<StoredItem>>()
        }
    )
) {
    DynamicTheme(ThemeOption.THEME_BLUE) {
        DivisionScreen(viewModel)
    }
}*/

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