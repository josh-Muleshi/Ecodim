package cd.wayupdotdev.ecodim.app.di

import android.content.Context
import android.content.SharedPreferences
import cd.wayupdotdev.ecodim.core.data.repository.CommentRepositoryImpl
import cd.wayupdotdev.ecodim.core.data.repository.LessonRepositoryImpl
import cd.wayupdotdev.ecodim.core.domain.repository.CommentRepository
import cd.wayupdotdev.ecodim.core.domain.repository.LessonRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    singleOf(::LessonRepositoryImpl) bind LessonRepository::class
    singleOf(::CommentRepositoryImpl) bind CommentRepository::class
}