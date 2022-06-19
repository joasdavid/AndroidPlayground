package pt.joasvpereira.xorganizer.presentation.compose.navigation

sealed class ScreenNavigation(val route: String) {
    object MainScreen: ScreenNavigation(route = MAIN_SCREEN_ROUTE)

    object CreateDivisionScreen: ScreenNavigation(route = CREATE_DIVISION_SCREEN_ROUTE)

    object DivisionScreen: ScreenNavigation(route = DIVISION_SCREEN_ROUTE) {
        const val DIVISION_ID = ScreenNavigation.DIVISION_ID
        fun createNavigationRoute(id: Int) = "$DIVISION_SCREEN_BASE_ROUTE/$id"
    }

    object FolderScreen: ScreenNavigation(route = FOLDER_SCREEN_ROUTE) {
        const val FOLDER_ID = ScreenNavigation.FOLDER_ID
        fun createNavigationRoute(id: Int) = "$FOLDER_SCREEN_BASE_ROUTE/$id"
    }

    object ItemScreen: ScreenNavigation(route = ITEM_SCREEN_ROUTE) {
        const val ITEM_ID = ScreenNavigation.ITEM_ID
        fun createNavigationRoute(id: Int) = "$ITEM_SCREEN_BASE_ROUTE/$id"
    }

    object TestColorDynamicScreen: ScreenNavigation(route = DYNAMIC_SCREEN_ROUTE)

    object TestColorBlueScreen: ScreenNavigation(route = BLUE_SCREEN_ROUTE)

    object TestColorGreenScreen: ScreenNavigation(route = GREEN_SCREEN_ROUTE)

    companion object {
        private const val MAIN_SCREEN_ROUTE = "main_screen"

        private const val CREATE_DIVISION_SCREEN_ROUTE = "create_screen"

        private const val CREATE_FOLDER_SHEET_ROUTE = "create_folder_screen"

        private const val DIVISION_ID = "divisionId"
        private const val DIVISION_SCREEN_BASE_ROUTE = "division_screen"
        private const val DIVISION_SCREEN_ROUTE = "$DIVISION_SCREEN_BASE_ROUTE/{$DIVISION_ID}"

        private const val FOLDER_ID = "folderId"
        private const val FOLDER_SCREEN_BASE_ROUTE = "folder_screen"
        private const val FOLDER_SCREEN_ROUTE = "$FOLDER_SCREEN_BASE_ROUTE/{$FOLDER_ID}"

        private const val ITEM_ID = "itemId"
        private const val ITEM_SCREEN_BASE_ROUTE = "item_screen"
        private const val ITEM_SCREEN_ROUTE = "$ITEM_SCREEN_BASE_ROUTE/{$ITEM_ID}"
        
        private const val DYNAMIC_SCREEN_ROUTE = "color_screen_dynamic"

        private const val BLUE_SCREEN_ROUTE = "color_screen_blue"

        private const val GREEN_SCREEN_ROUTE = "color_screen_green"
    }
}