package pt.joasvpereira.xorganizer.compose.common.container

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Folder(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = contentColorFor(color),
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit = {}
) {
    ConstraintLayout(modifier = modifier) {
        val (backCardRef, frontCardRef, paperRef, sideRef) = createRefs()
        val frontCardGuideline = createGuidelineFromTop(0.1f)
        val paperGuideline = createGuidelineFromTop(0.05f)
        val paperBottomGuideline = createGuidelineFromTop(0.2f)
        val sideGuideline = createGuidelineFromStart(.15f)
        Card(
            modifier = Modifier
                .constrainAs(backCardRef) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .background(if (isDarkTheme) Color.Black else Color.White),
            colors = CardDefaults.cardColors(containerColor = color.copy(alpha = .5f)),
            shape = RoundedCornerShape(5.dp),
            content = {}
        )

        Box(modifier = Modifier
            .constrainAs(paperRef) {
                top.linkTo(paperGuideline)
                end.linkTo(parent.end, 1.dp)
                start.linkTo(parent.start, 5.dp)
                bottom.linkTo(paperBottomGuideline)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color.LightGray,
                        Color.LightGray,
                    )
                )
            ))

        Surface(
            modifier = Modifier
                .constrainAs(frontCardRef) {
                    top.linkTo(frontCardGuideline)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .clip(
                    shape = RoundedCornerShape(bottomEnd = 5.dp, topEnd = 5.dp, bottomStart = 5.dp)
                ),
            color = color,
            contentColor = contentColor
        ) {
            Box(contentAlignment = contentAlignment) {
                content()
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(sideRef) {
                    top.linkTo(parent.top)
                    end.linkTo(sideGuideline)
                    start.linkTo(parent.start)
                    bottom.linkTo(frontCardRef.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
                ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideFolder(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = contentColorFor(color),
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit = {}
) {
    ConstraintLayout(modifier = modifier) {
        val (backCardRef, frontCardRef, paperRef, sideRef) = createRefs()
        val frontCardGuideline = createGuidelineFromEnd(0.1f)
        val paperGuideline = createGuidelineFromEnd(0.05f)
        val paperStartGuideline = createGuidelineFromEnd(0.20f)
        val sideGuideline = createGuidelineFromBottom(.15f)
        Card(
            modifier = Modifier
                .constrainAs(backCardRef) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .background(if (isDarkTheme) Color.Black else Color.White),
            colors = CardDefaults.cardColors(containerColor = color.copy(alpha = .5f)),
            shape = RoundedCornerShape(5.dp)
        ) {}

        Box(modifier = Modifier
            .constrainAs(paperRef) {
                top.linkTo(parent.top, 1.dp)
                end.linkTo(paperGuideline)
                start.linkTo(paperStartGuideline)
                bottom.linkTo(parent.bottom, 5.dp)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color.LightGray,
                        Color.LightGray,
                        Color.White,
                    )
                )
            )
        )

        Surface(
            modifier = Modifier
                .constrainAs(frontCardRef) {
                    top.linkTo(parent.top)
                    end.linkTo(frontCardGuideline)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .clip(
                    shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp)
                ),
            color = color,
            contentColor = contentColor
        ) {
            content()
        }

        Box(
            modifier = Modifier
                .constrainAs(sideRef) {
                    top.linkTo(sideGuideline)
                    end.linkTo(parent.end)
                    start.linkTo(frontCardRef.end)
                    bottom.linkTo(frontCardRef.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp)
                ),
        )
    }
}

@Preview()
@Composable
fun FolderPreview() {
    Row {
        SideFolder(
            Modifier
                .height(90.dp)
                .width(45.dp)
        ) {
            SimpleSpace(size = 50.dp)
            Text("sadas")
        }
        SimpleSpace(size = 20.dp)
        Folder(
            Modifier
                .height(45.dp)
                .width(90.dp)
        ) {
            SimpleSpace(size = 50.dp)
            Text("sadas")
        }
    }
}