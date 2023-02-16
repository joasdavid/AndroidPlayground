package pt.joasvpereira.coreui.selector

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.R
import pt.joasvpereira.coreui.shield.First2WordsHighlight
import pt.joasvpereira.coreui.shield.NameShield

@Composable
fun RequestContentPermission() {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    Column() {
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Text(text = "Pick image")
        }

        Spacer(modifier = Modifier.height(12.dp))

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(400.dp)
                )
            }
        }

    }
}

@Composable
fun UploadImagePlaceHolder(
    modifier: Modifier = Modifier,
    bitmap: Bitmap? = null,
    emptyText: String,
    onUploadClick: () -> Unit,
    onClearImageClick: () -> Unit,
) {
    Box(modifier = modifier.size(150.dp)) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .border(border = BorderStroke(2.dp, MaterialTheme.colorScheme.primaryContainer), shape = CircleShape),
                contentScale = ContentScale.FillBounds
            )
            Surface(
                Modifier
                    .padding(top = 7.dp, start = 7.dp)
                    .clip(CircleShape)
                    .size(30.dp)
                    .clickable {
                        onClearImageClick()
                    }
                    .align(Alignment.TopStart),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                tonalElevation = 100.dp,
                shadowElevation = 100.dp
            ) {
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_trash_can_outline),
                        contentDescription = "",
                        modifier.align(Alignment.Center)
                    )
                }
            }
        } else {
            NameShield(
                text = emptyText,
                borderSize = 0.dp,
                textStrategy = First2WordsHighlight(emptySymbol = ' '),
                textStyle = MaterialTheme.typography.displayLarge.copy(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Surface(
            Modifier
                .clip(CircleShape)
                .size(48.dp)
                .clickable {
                    onUploadClick()
                }
                .align(Alignment.BottomEnd),
            color = MaterialTheme.colorScheme.tertiaryContainer,
            tonalElevation = 100.dp,
            shadowElevation = 100.dp
        ) {
            Box {
                Icon(
                    painter = painterResource(id = R.drawable.ic_upload),
                    contentDescription = "",
                    modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
fun UploadImagePlaceHolderPreview() {
    DynamicTheme {
        val context = LocalContext.current
        val drawable = AppCompatResources.getDrawable(context, R.drawable.background)
        UploadImagePlaceHolder(modifier = Modifier, drawable?.toBitmap(),"", onUploadClick = {}) {}
    }
}