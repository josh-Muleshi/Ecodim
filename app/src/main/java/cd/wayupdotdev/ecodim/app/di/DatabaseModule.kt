package cd.wayupdotdev.ecodim.app.di

import android.content.Context
import androidx.room.Room
import cd.wayupdotdev.ecodim.core.data.local.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get<Context>(), AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().lessonDao() }
}
