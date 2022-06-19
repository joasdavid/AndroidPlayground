package pt.joasvpereira.xorganizer.repository

import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource

class InMemory: DivisionDataSource {

    private val divs = mutableListOf(
        Division(id = 1, name = "Division 1", description = "Description 1", iconId = 0, nrBox = 2, nrItem = 3, themeId = 0),
        Division(id = 2, name = "Division 2", description = "", iconId = 0, nrBox = 1, nrItem = 1, themeId = 1),
        Division(id = 3, name = "Division 3", description = "Long discription asdasldkslad;askd;lsak;dasdasdl", iconId = 0, nrBox = 100, nrItem = 40, themeId = 2),
    )

    override suspend fun getDivisions(): List<Division> = divs

    override suspend fun singleDivisions(id: Int): Division = divs.find { it.id == id }!!
}