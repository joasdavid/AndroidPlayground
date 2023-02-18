package pt.joasvpereira.xorganizer.presentation.compose.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.box.BoxImage
import pt.joasvpereira.coreui.scaffold.AppScaffold

@Composable
fun SplashScreen() {
    AppScaffold {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BoxImage(modifier = Modifier.size(100.dp))
                SimpleSpace(size = 50.dp)
                LinearProgressIndicator(progress = .5f)
            }
        }
    }
}

@Preview()
@Composable
fun SpashScreenPreview() {
    DynamicTheme {
        SplashScreen()
    }
}