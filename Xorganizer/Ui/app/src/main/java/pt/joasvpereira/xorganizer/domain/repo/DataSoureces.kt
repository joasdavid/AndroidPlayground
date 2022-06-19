package pt.joasvpereira.xorganizer.domain.repo

import pt.joasvpereira.xorganizer.domain.model.Division
import java.util.concurrent.Flow

interface DivisionDataSource {
    suspend fun getDivisions(): List<Division>
    suspend fun singleDivisions(id : Int): Division
}