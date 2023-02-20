package com.joasvpereira.main.compose.division

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Visibility
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.box.BoxImage
import pt.joasvpereira.main.R


class DivisionCreateButtonsState(
    isOpen: Boolean = false
) {
    private var isOpenState= mutableStateOf(isOpen)
    var isOpen
        get() = isOpenState.value
        private set(value) {
            isOpenState.value = value
        }

    private var isButtonsVisibleState = mutableStateOf(isOpen)
    var isButtonsVisible
        get() = isButtonsVisibleState.value
        private set(value) {
            isButtonsVisibleState.value = value
        }

    fun toggle(){
        isOpen = !isOpen
        if (isOpen) {
            isButtonsVisible = true
        }
    }

    fun animationEnd() {
        isButtonsVisible = isOpen
    }

    fun provideBaseMargin(): () -> Dp = {
        if (isOpen) 100.dp else 0.dp
    }
}

@Composable
fun rememberDivisionCreateButtonsState(isOpen: Boolean = false) = remember {
    DivisionCreateButtonsState(isOpen = isOpen)
}

@Composable
fun DivisionCreateButtons(
    divisionCreateButtonsState : DivisionCreateButtonsState = rememberDivisionCreateButtonsState(),
    onAddBoxClick: () -> Unit,
    onAddItemClick: () -> Unit,
    bottomEdgePadding: Dp = 0.dp
) {
    val margin by animateDpAsState(
        targetValue = divisionCreateButtonsState.provideBaseMargin().invoke(),
        finishedListener = { divisionCreateButtonsState.animationEnd() }
    )
    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(bottom = bottomEdgePadding)) {
        val (baseButton, itemButton, boxButton) = createRefs()
        FloatingActionButton(
            onClick = onAddItemClick,
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(itemButton) {
                    bottom.linkTo(parent.bottom, margin = margin * 2)
                    end.linkTo(parent.end)
                    visibility = if (divisionCreateButtonsState.isButtonsVisible) Visibility.Visible else Visibility.Gone
                },
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_item), contentDescription = null)
        }

        FloatingActionButton(
            onClick = onAddBoxClick,
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(boxButton) {
                    bottom.linkTo(parent.bottom, margin = margin)
                    end.linkTo(parent.end)
                    visibility = if (divisionCreateButtonsState.isButtonsVisible) Visibility.Visible else Visibility.Gone
                },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ) {
            BoxImage(modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.onTertiaryContainer)
        }

        FloatingActionButton(
            onClick = { divisionCreateButtonsState.toggle() },
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(baseButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            val vecIcon = if (divisionCreateButtonsState.isOpen) Icons.Default.Close else Icons.Default.Add
            Icon(imageVector = vecIcon, contentDescription = null)
        }
    }
}

@Preview
@Composable
private fun DivisionCreateButtonsPreview() {
    DynamicTheme() {
        DivisionCreateButtons(
            rememberDivisionCreateButtonsState(false),
            onAddBoxClick = {},
            onAddItemClick = {},
        )
    }
}