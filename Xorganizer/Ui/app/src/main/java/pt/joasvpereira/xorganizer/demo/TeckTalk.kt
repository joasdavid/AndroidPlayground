package pt.joasvpereira.xorganizer.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.joasvpereira.xorganizer.R

@Preview
@Composable
private fun DeclarativeApproach() {
    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo_mobile_factory),
            contentDescription = "Mobile factory Logo."
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "This is an example of Compose...")

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Button(onClick = {  }) {
                Icon(painter = painterResource(id = R.drawable.dislike), contentDescription = "Dislike button")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.like), contentDescription = "Like button")
            }
        }
    }
}