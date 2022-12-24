package pt.joasvpereira.xorganizer.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.joasvpereira.xorganizer.repository.local.dao.DivisionDao
import pt.joasvpereira.xorganizer.repository.local.entities.Box
import pt.joasvpereira.xorganizer.repository.local.entities.Division
import pt.joasvpereira.xorganizer.repository.local.entities.Item
import pt.joasvpereira.xorganizer.repository.local.entities.Session

@Database(entities = [Division::class, Box::class, Session::class, Item::class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun userDao(): DivisionDao
}