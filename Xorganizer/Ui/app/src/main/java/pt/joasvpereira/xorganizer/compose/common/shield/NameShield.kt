package pt.joasvpereira.xorganizer.compose.common.shield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface TextHighlightStrategy {
    fun capture(from: String): String
}

class First2WordsHighlight(private val isUpperCase: Boolean = true) : TextHighlightStrategy {
    override fun capture(from: String): String =
        from.trim().split(" ").run {
            val firstWord = this[0]
            when {
                firstWord.isBlank() -> "?"
                size >= 2 -> {
                    val secondWord = this[1]
                    "${firstWord[0]}${secondWord[0]}"
                }
                firstWord.length >= 2 -> "${firstWord[0]}${firstWord[1]}"
                else -> "${firstWord[0]}}"
            }
        }.run {
            if (isUpperCase) {
                return@run this.uppercase()
            }
            this
        }
}

@Composable
fun NameShield(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    text: String,
    textColor: Color = contentColorFor(backgroundColor = backgroundColor),
    textStyle: TextStyle = LocalTextStyle.current,
    textStrategy: TextHighlightStrategy = First2WordsHighlight(),
    innerPadding: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    borderSize: Dp = 2.dp,
    shape: Shape = CircleShape,
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .then(
                    if (borderSize > 0.dp) {
                        Modifier.border(
                            color = borderColor,
                            width = borderSize,
                            shape = shape
                        )
                    } else Modifier
                )
                .padding(innerPadding + borderSize),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = textStrategy.capture(text),
                color = textColor,
                style = textStyle
            )
        }
    }
}

@Preview
@Composable
fun NameShieldPreview() {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        NameShield(text = "asd", modifier = Modifier.size(48.dp))
        NameShield(text = "   ", modifier = Modifier.size(48.dp))
        NameShield(text = "qe w", modifier = Modifier.size(48.dp))
    }
}