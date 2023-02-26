package com.joasvpereira.main.presentation.dashboard

import android.graphics.Bitmap
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import com.joasvpereira.main.domain.data.DashboardDivision

data class DashboardFeatureScreenState(
    val isLoading: Boolean = true,
    val sessionName: String = "",
    val sessionImage: Bitmap? = null,
    val divisions: List<DashboardDivision> = listOf(),
    val deleteEvent: DeleteEvent? = null,
)

data class DeleteEvent(
    val division: DashboardDivision,
    val confirmationText: String,
    val isNameMatchingConfirmation: Boolean = division.name.toUpperCase(Locale.current) == confirmationText,
)
