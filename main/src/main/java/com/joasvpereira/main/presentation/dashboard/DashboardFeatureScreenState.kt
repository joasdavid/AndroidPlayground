package com.joasvpereira.main.presentation.dashboard

import android.graphics.Bitmap
import com.joasvpereira.main.domain.data.DashboardDivision

data class DashboardFeatureScreenState(
    val isLoading: Boolean = true,
    val sessionName: String = "",
    val sessionImage: Bitmap? = null,
    val divisions: List<DashboardDivision> = listOf(),
)
