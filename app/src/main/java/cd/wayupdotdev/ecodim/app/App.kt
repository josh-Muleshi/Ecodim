package cd.wayupdotdev.ecodim.app

import android.app.Application
import cd.wayupdotdev.ecodim.app.di.appModule
import cd.wayupdotdev.ecodim.app.di.firebaseModule
import cd.wayupdotdev.ecodim.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, viewModelModule, firebaseModule)
        }
    }
}