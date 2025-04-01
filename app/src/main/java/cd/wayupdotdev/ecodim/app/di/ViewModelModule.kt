package cd.wayupdotdev.ecodim.app.di

import cd.wayupdotdev.ecodim.features.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

//    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeViewModel)
//    viewModelOf(::SignInViewModel)
//    viewModelOf(::SettingViewModel)
//    viewModelOf(::ProfileViewModel)
//    viewModelOf(::ProductDetailViewModel)
}