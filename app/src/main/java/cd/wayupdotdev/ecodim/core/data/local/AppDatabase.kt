package cd.wayupdotdev.ecodim.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cd.wayupdotdev.ecodim.core.data.local.dao.LessonDao
import cd.wayupdotdev.ecodim.core.data.local.entity.LessonEntity

@Database(entities = [LessonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lessonDao(): LessonDao
}
