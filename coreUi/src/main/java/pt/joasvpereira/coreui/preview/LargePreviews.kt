package pt.joasvpereira.coreui.preview

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Tablet", group = "Large", device = Devices.NEXUS_10)
annotation class LargePreview

@Preview(name = "Foldable", group = "Medium", device = Devices.FOLDABLE)
annotation class FoldablePreview
