package com.anton.dobrogorsky.usersposts.di

import com.anton.dobrogorsky.usersposts.app.App
import com.anton.dobrogorsky.usersposts.flow.posts.UserPostsViewModel
import com.anton.dobrogorsky.usersposts.flow.users.UserListViewModel
import com.anton.dobrogorsky.usersposts.repository.PostRepository
import com.anton.dobrogorsky.usersposts.repository.UserRepository
import com.anton.dobrogorsky.usersposts.service.api.PostsApi
import com.anton.dobrogorsky.usersposts.service.api.UsersApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Modules {

    val viewModelModule = module {
        viewModel { UserListViewModel(userRepository = get()) }
        viewModel { UserPostsViewModel(postRepository = get()) }
    }

    val networkModule = module {
        factory { provideUsersApi(retrofit = get()) }
        factory { providePostsApi(retrofit = get()) }
        single { provideRetrofit() }
    }

    val repositoryModule = module {
        single { provideUserRepository(usersApi = get()) }
        single { providePostsRepository(postsApi = get()) }
    }

    fun provideUserRepository(usersApi: UsersApi): UserRepository = UserRepository(usersApi)

    fun providePostsRepository(postsApi: PostsApi): PostRepository = PostRepository(postsApi)

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(App.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideUsersApi(retrofit: Retrofit): UsersApi = retrofit.create(UsersApi::class.java)

    fun providePostsApi(retrofit: Retrofit): PostsApi = retrofit.create(PostsApi::class.java)

}