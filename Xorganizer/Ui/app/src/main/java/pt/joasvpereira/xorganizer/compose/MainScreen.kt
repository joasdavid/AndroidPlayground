package pt.joasvpereira.xorganizer.compose

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.CreateDivisionScreen
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.IconAndCounter
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.IconData
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.BedSolid
import compose.icons.lineawesomeicons.HomeSolid
import compose.icons.lineawesomeicons.PlusSolid
import compose.icons.lineawesomeicons.TableSolid
import compose.icons.lineawesomeicons.TabletAltSolid
import pt.joasvpereira.xorganizer.R
import pt.joasvpereira.xorganizer.ui.theme.DynamicTheme
import pt.joasvpereira.xorganizer.ui.theme.ThemeOption

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    var ss: Boolean by remember { mutableStateOf(false) }
    if (!ss) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(50.dp))
                Text(text = "Welcome", style = MaterialTheme.typography.titleLarge)
                Text(text = "Joás V. Pereira", style = MaterialTheme.typography.titleSmall)
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Devisions: ", style = MaterialTheme.typography.titleMedium)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 0.dp,
                    top = 16.dp,
                    end = 0.dp,
                    bottom = 16.dp
                )
            ) {
                items(div.size) { index: Int ->
                    DivisionListItem(
                        title = div[index].title,
                        description = div[index].description,
                        vectorImg = div[index].vectorImg,
                        boxCount = div[index].boxCount,
                        childCount = div[index].childCount,
                        option = div[index].option
                    )
                }
                item {
                    AddActionItem(action = { ss = !ss })
                }
            }
        }
    } else {
        CreateDivisionScreen()
    }
}

@Composable
fun AddActionItem(action: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(5.dp))
            .height(180.dp)
            .clickable {
                action()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(90.dp),
            imageVector = LineAwesomeIcons.PlusSolid,
            contentDescription = "",
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DivisionListItem(
    title: String,
    description: String,
    vectorImg: ImageVector,
    boxCount: Int,
    childCount: Int,
    option: ThemeOption = ThemeOption.THEME_DEFAULT,
    onclick: ()-> Unit = {}
) {
    DynamicTheme(option = option) {
        Card(onClick = onclick,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(5.dp)
                )
                .height(180.dp)
            //elevation = CardElevation.shadowElevation(interactionSource = 5.dp)
        ) {
            Box(Modifier.background(color = MaterialTheme.colorScheme.primaryContainer)) {
                StyleShape(Modifier.align(Alignment.BottomEnd))
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
                    Icon(
                        imageVector = vectorImg,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    Text(text = title, color = MaterialTheme.colorScheme.onPrimaryContainer)

                    Spacer(modifier = Modifier.size(4.dp))

                    description.takeIf { it.isNotBlank() }?.run {
                        Text(
                            text = this,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    Row(
                        modifier = Modifier
                    ) {
                        IconAndCounter(
                            iconData = IconData(painterResource(R.drawable.ic_box), ""),
                            count = boxCount,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        IconAndCounter(
                            iconData = IconData(painterResource(R.drawable.ic_baseline_build_24), ""),
                            count = childCount,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun StyleShape(modifier: Modifier) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 40.dp, end = 30.dp)
                .size(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = .4f),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 50.dp)
                .size(60.dp)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = .4f),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(90.dp)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = .7f),
                    shape = RoundedCornerShape(topStart = 200.dp)
                )
        )
    }
}

data class itemTest(
    val title: String,
    val description: String,
    val vectorImg: ImageVector,
    val boxCount: Int,
    val childCount: Int,
    val option: ThemeOption = ThemeOption.THEME_DEFAULT
)

val div = listOf(
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
        childCount = 4,
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

@Preview
@Composable
fun DivisionListItemPreview() {
    Column {
        DivisionListItem(
            title = "Division 1",
            description = "Description 1",
            vectorImg = LineAwesomeIcons.HomeSolid,
            boxCount = 2,
            childCount = 3
        )
        DivisionListItem(
            title = "Division 2",
            description = "",
            vectorImg = LineAwesomeIcons.TableSolid,
            boxCount = 1,
            childCount = 4,
            option = ThemeOption.THEME_BLUE
        )
        DivisionListItem(
            title = "Division 3",
            description = "Long discription asdasldkslad;askd;lsak;dasdasdl",
            vectorImg = LineAwesomeIcons.TableSolid,
            boxCount = 100,
            childCount = 40,
            option = ThemeOption.THEME_GREEN
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DivisionListItemPreviewDark() {
    Column {
        DivisionListItem(
            title = "Division 1",
            description = "Description 1",
            vectorImg = LineAwesomeIcons.HomeSolid,
            boxCount = 2,
            childCount = 3
        )
        DivisionListItem(
            title = "Division 2",
            description = "",
            vectorImg = LineAwesomeIcons.TableSolid,
            boxCount = 1,
            childCount = 4,
            option = ThemeOption.THEME_BLUE
        )
        DivisionListItem(
            title = "Division 3",
            description = "Long discription asdasldkslad;askd;lsak;dasdasdl",
            vectorImg = LineAwesomeIcons.TableSolid,
            boxCount = 100,
            childCount = 40,
            option = ThemeOption.THEME_GREEN
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
        MainScreen()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreviewDark() {
        MainScreen()
}

