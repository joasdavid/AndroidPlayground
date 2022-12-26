package pt.joasvpereira.sessionfeature.compose.select.session

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.sessionfeature.R

@Composable
fun SelectSessionScreen() {
    DynamicTheme {
        Surface(Modifier.fillMaxSize()) {
            Box {
                val backgroundRes = R.drawable.background
                ScreenBackground(backgroundRes)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SimpleSpace(100.dp)
                    Text("Please Select your profile:", style = MaterialTheme.typography.titleLarge)
                    SimpleSpace(20.dp)

                }
            }
        }
    }
}

@Preview
@Composable
fun SelectSessionScreenPreview() {
    SelectSessionScreen()
}

@Composable
private fun ScreenBackground(backgroundRes: Int) {
    Image(
        painter = painterResource(id = backgroundRes),
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}