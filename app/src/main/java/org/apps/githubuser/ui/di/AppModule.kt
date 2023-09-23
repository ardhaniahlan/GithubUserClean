package org.apps.githubuser.ui.di

import org.apps.core.domain.UserInteractor
import org.apps.core.domain.UserUseCase
import org.apps.githubuser.ui.detail.DetailViewModel
import org.apps.githubuser.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { DetailViewModel(get(),get()) }
}