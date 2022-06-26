package pt.joasvpereira.xorganizer.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import pt.joasvpereira.xorganizer.domain.model.Box
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.model.StoredItem
import pt.joasvpereira.xorganizer.domain.repo.BoxDataSource
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
import pt.joasvpereira.xorganizer.domain.repo.FromBox
import pt.joasvpereira.xorganizer.domain.repo.FromDivision
import pt.joasvpereira.xorganizer.domain.repo.StoredItemDataSource
import pt.joasvpereira.xorganizer.presentation.compose.division.SingleItem

class InMemory: DivisionDataSource, BoxDataSource, StoredItemDataSource {

    private val divisionFlow  = MutableSharedFlow<List<Division>>(replay = 1)

    override suspend fun getDivisions() = divisionFlow.apply {
        emit(divs)
    }

    override suspend fun singleDivisions(id: Int): Division = divs.find { it.id == id }!!

    override suspend fun addDivision(division: Division) {
        division.copy(id = autoIncrement()).let {
            divs.add(it)
            divisionFlow.emit(divs)
            boxMap[it.id] = mutableListOf()
            itemMap[it.id] = mutableListOf()
        }
    }

    private val boxFlow = MutableSharedFlow<List<Box>>(replay = 1)

    override suspend fun getBoxes(from: FromDivision) = boxFlow.apply {
        emit(boxMap[from.id]!!.toList())
    }

    override suspend fun singleBox(id: Int) = boxMap.values.flatten().find { it.id == id }!!

    private val itemFlow = MutableSharedFlow<List<StoredItem>>(replay = 1)

    override suspend fun getItems(from: FromDivision) = itemFlow.apply {
        emit(itemMap[from.id]!!.toList())
    }

    override suspend fun getItems(from: FromBox) = itemFlow.apply {
        val list = listOf(
            StoredItem(
                id = -1,
                name = "Carregador",
                description = "",
                tags = listOf("tipe c"),
                isUsed = false
            ),
            StoredItem(
                id = -1,
                name = "Carregador",
                description = "",
                tags = listOf("tipe c"),
                isUsed = false
            ),
            StoredItem(
                id = -1,
                name = "cabo c to usb",
                description = "",
                tags = listOf("tipe c", "cabo", "usb"),
                isUsed = false
            ),
        )
        emit(list)
    }

    override suspend fun singleItem(id: Int) = itemMap.values.flatten().find { it.id == id }!!

    // region heppers
    private val divs = mutableListOf(
        Division(id = 1, name = "Division 1", description = "Description 1", iconId = 0, nrBox = 2, nrItem = 3, themeId = 0),
        Division(id = 2, name = "Division 2", description = "", iconId = 0, nrBox = 1, nrItem = 4, themeId = 1),
        Division(id = 3, name = "Division 3", description = "Long discription asdasldkslad;askd;lsak;dasdasdl", iconId = 0, nrBox = 100, nrItem = 40, themeId = 2),
    )

    private val boxMap = HashMap<Int, MutableList<Box>>().apply {
        put(1, createDummyListOfBox(divs.find { it.id  == 1 }?.nrBox ?: 0))
        put(2, createDummyListOfBox(divs.find { it.id  == 2 }?.nrBox ?: 0))
        put(3, createDummyListOfBox(divs.find { it.id  == 3 }?.nrBox ?: 0))
    }

    private val itemMap = HashMap<Int, MutableList<StoredItem>>().apply {
        put(1, mutableListOf(
            StoredItem(
                id = getNextItemId(),
                name = "Carregador",
                description = "",
                tags = listOf("tipe c"),
                isUsed = false
            ),
            StoredItem(
                id = getNextItemId(),
                name = "Carregador",
                description = "",
                tags = listOf("tipe c"),
                isUsed = false
            ),
            StoredItem(
                id = getNextItemId(),
                name = "cabo c to usb",
                description = "",
                tags = listOf("tipe c", "cabo", "usb"),
                isUsed = false
            ),
        ))
        put(2, mutableListOf(
            StoredItem(
                id = getNextItemId(),
                name = "Carregador",
                description = "",
                tags = listOf("tipe c"),
                isUsed = false
            ),
            StoredItem(
                id = getNextItemId(),
                name = "Carregador",
                description = "",
                tags = listOf("tipe c"),
                isUsed = false
            ),
            StoredItem(
                id = getNextItemId(),
                name = "cabo c to usb",
                description = "",
                tags = listOf("tipe c", "cabo", "usb"),
                isUsed = true
            ),
            StoredItem(
                id = getNextItemId(),
                name = "base wirelless",
                description = "",
                tags = listOf("tipe c", "preto", "MI", "10v", "setOf2", "plug in", "pixel 5"),
                isUsed = true
            ),
        ))
        put(3, generateListOfRandomItems(divs.find { it.id == 3 }?.nrItem ?: 0))
    }

    private fun generateListOfRandomItems(nrOfItems: Int) = mutableListOf<StoredItem>().apply {
        (0..nrOfItems).forEach { i ->
            add(StoredItem(id = getNextItemId(), name = "random item $i", description = "", tags = listOf(), isUsed = false))
        }
    }

    private fun autoIncrement(): Int {
        return divs.maxOf { it.id } + 1
    }

    private fun createDummyListOfBox(boxCount : Int) =  mutableListOf<Box>().apply {
        for (i in 0 until boxCount) {
            val id = getNextBoxId()
           this.add(
                Box(id = id, name = "Box $id", description = ""). run {
                    if (id % 2 == 0) this.copy(description = "description $id")
                    else this
                }
            )
        }
    }

    companion object {
        private var nextFreeIdForBox: Int = 0
        private fun getNextBoxId() = nextFreeIdForBox++
        private var nextFreeIdForItem: Int = 0
        private fun getNextItemId() = nextFreeIdForItem++
    }
    //endregion
}