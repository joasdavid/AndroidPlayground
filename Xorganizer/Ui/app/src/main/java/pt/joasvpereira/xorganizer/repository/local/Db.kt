package pt.joasvpereira.xorganizer.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.joasvpereira.xorganizer.repository.local.dao.DivisionDao
import pt.joasvpereira.xorganizer.repository.local.entities.Division

@Database(entities = [Division::class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun userDao(): DivisionDao
}