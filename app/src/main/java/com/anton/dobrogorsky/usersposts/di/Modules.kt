package com.anton.dobrogorsky.usersposts.di

import com.anton.dobrogorsky.usersposts.flow.users.UserListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {

    val viewModelModule = module {
        viewModel { UserListViewModel() }
    }

}