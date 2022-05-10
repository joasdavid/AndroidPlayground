package pt.joasvpereira.xorganizer.demo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.xorganizer.R


@Composable
private fun DeclarativeApproach(
    onLikeClick: () -> Unit = {},
    onDislikeClick: () -> Unit = {},
) {
    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo_mobile_factory),
            contentDescription = "Mobile factory Logo."
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "This is an example of Compose...")

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Button(onClick = onDislikeClick) {
                Icon(painter = painterResource(id = R.drawable.dislike), contentDescription = "Dislike button")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = onLikeClick) {
                Icon(painter = painterResource(id = R.drawable.like), contentDescription = "Like button")
            }
        }
    }
}

@Composable
fun FunctionsAndFunctions() {
    Column(content = { MyContent(emptyList()) })

    Column {
        MyContent(emptyList())
    }
}

@Composable
fun MyContent(list: List<String>) {
    if (list.isEmpty()) return

    Column {
        list.forEach { Text(text = it) }
        list.joinToString(
            separator = " ",
            prefix = "Full list: ",
            postfix = "."
        ).run {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = this)
        }
    }
}

//@Preview()
@Composable
fun DeclarativeApproachPreview() {
    DeclarativeApproach()
}

data class DemoData(
    var name: String = "",
    var msg: String = "",
    var isFirstScreen: Boolean = false
)

class vm : ViewModel() {
    val data = MutableLiveData(DemoData())
}

@ExperimentalMaterial3Api
@Composable
fun Demo() {
    var name by remember { mutableStateOf("") }
    var msg by remember { mutableStateOf("") }
    var isFirstScreen by remember { mutableStateOf(true) }
    AnimatedVisibility(visible = isFirstScreen) {
        RateScreen(
            name = name,
            onNameChange = { name = it },
            onRateChange = {
                msg = it
                if (name.isNotBlank())
                    isFirstScreen = false
            }
        )
    }
    AnimatedVisibility(visible = !isFirstScreen) {
        ResultScreen(msg, onResetClick = { isFirstScreen = true })
    }
}

@Composable
private fun ResultScreen(msg: String, onResetClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = msg, style = MaterialTheme.typography.titleLarge)
        SimpleSpace(size = 5.dp)
        Text(
            text = "reset",
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            modifier = Modifier.clickable { onResetClick() })
    }
}

@Composable
private fun RateScreen(
    name: String,
    onNameChange: (String) -> Unit,
    onRateChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeclarativeApproach(
            onLikeClick = {
                onRateChange("$name likes Mobile Factory.")
            },
            onDislikeClick = {
                onRateChange("$name dislikes Mobile Factory.")
            }
        )
        SimpleSpace(size = 20.dp)
        OutlinedTextField(value = name, onValueChange = { onNameChange(it) }, label = { Text(text = "Name") })
        SimpleSpace(size = 20.dp)
    }
}

@ExperimentalMaterial3Api
@Preview()
@Composable
fun DemoPreview() {
    Demo()
}