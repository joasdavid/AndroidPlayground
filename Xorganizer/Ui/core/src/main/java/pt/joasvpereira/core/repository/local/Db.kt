package pt.joasvpereira.core.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.joasvpereira.core.repository.local.dao.DivisionDao
import pt.joasvpereira.core.repository.local.dao.SessionDao
import pt.joasvpereira.core.repository.local.entities.Box
import pt.joasvpereira.core.repository.local.entities.Division
import pt.joasvpereira.core.repository.local.entities.Item
import pt.joasvpereira.core.repository.local.entities.Session

@Database(entities = [Division::class, Box::class, Session::class, Item::class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun userDao(): DivisionDao
    abstract fun sessionDao(): SessionDao
}