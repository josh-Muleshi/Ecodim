package cd.wayupdotdev.ecodim.app.di

import cd.wayupdotdev.ecodim.features.comments.CommentViewModel
import cd.wayupdotdev.ecodim.features.favorite.FavoriteViewModel
import cd.wayupdotdev.ecodim.features.home.HomeViewModel
import cd.wayupdotdev.ecodim.features.topicDetail.TopicDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::TopicDetailViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::FavoriteViewModel)
    viewModelOf(::CommentViewModel)
}