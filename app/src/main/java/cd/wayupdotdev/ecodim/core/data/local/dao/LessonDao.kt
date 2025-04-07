package cd.wayupdotdev.ecodim.core.data.local.dao

import androidx.room.*
import cd.wayupdotdev.ecodim.core.data.local.entity.LessonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {

    @Query("SELECT * FROM lessons ORDER BY updatedAt DESC")
    fun getAllLessons(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE isFavorite = 1 ORDER BY updatedAt DESC")
    fun getFavoriteLessons(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE id = :lessonId LIMIT 1")
    fun getLessonByUid(lessonId: String): Flow<LessonEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<LessonEntity>)

    @Query("UPDATE lessons SET isFavorite = :isFavorite WHERE id = :lessonId")
    suspend fun updateFavorite(lessonId: String, isFavorite: Boolean)

    @Update
    suspend fun updateFavorite(lesson: LessonEntity)
}
