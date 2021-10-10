package com.example.blogapp

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.blogapp.data.BloggingRepo
import com.example.blogapp.data.remote.RemoteDataSource
import com.example.blogapp.data.remote.RetrofitProvider
import com.example.blogapp.viewmodels.AuthorViewModel
import com.example.blogapp.viewmodels.AuthorViewModelFactory
//import com.example.blogapp.viewmodels.WeatherViewModelFactory
import org.kodein.di.*
//import org.kodein.di.Kodein
//import org.kodein.di.KodeinAware
//import org.kodein.di.android.x.AndroidLifecycleScope
//import org.kodein.di.bindings.KodeinBinding
//import org.kodein.di.generic.bind
//import org.kodein.di.generic.instance
//import org.kodein.di.generic.provider
//import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import java.lang.reflect.TypeVariable

class BlogginApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }

     val di = DI {
        bind<Retrofit>() with singleton { RetrofitProvider.getInstance() }
        bind<RemoteDataSource>() with  singleton { RemoteDataSource(instance()) }
        bind<BloggingRepo>() with  singleton { BloggingRepo(instance(),this@BlogginApp) }
         bind<AuthorViewModelFactory>() with provider { AuthorViewModelFactory(this@BlogginApp) }
    }
}