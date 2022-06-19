package pt.joasvpereira.xorganizer.presentation.mapper

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.QuestionCircle
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.presentation.compose.DivisionHolder
import pt.joasvpereira.xorganizer.presentation.theme.ThemeOption

class DivisionsMapper: BaseMapper<DivisionHolder, Division> {
    override fun mapToPresentation(from: Division): DivisionHolder = with(from) {
        DivisionHolder(
            id = id,
            title = name,
            description = description,
            vectorImg = mapImage(iconId),
            boxCount = nrBox,
            childCount = nrItem,
            option = mapTheme(themeId)
        )
    }

    override fun mapToDomain(from: DivisionHolder): Division = with(from) {
        Division(
            id = id ?: -1,
            name = title,
            description = description,
            iconId = reverseMapImage(vectorImg),
            nrBox = boxCount,
            nrItem = childCount,
            themeId = reverseMapTheme(option)
        )
    }

    private fun mapTheme(themeId: Int): ThemeOption = when(themeId) {
        1 -> ThemeOption.THEME_BLUE
        2 -> ThemeOption.THEME_GREEN
        else -> ThemeOption.THEME_DEFAULT
    }

    private fun mapImage(iconId: Int): ImageVector = LineAwesomeIcons.QuestionCircle

    private fun reverseMapTheme(option: ThemeOption): Int = when(option) {
        ThemeOption.THEME_BLUE -> 1
        ThemeOption.THEME_GREEN -> 2
        else -> 0
    }

    private fun reverseMapImage(vectorImg: ImageVector): Int = 0
}