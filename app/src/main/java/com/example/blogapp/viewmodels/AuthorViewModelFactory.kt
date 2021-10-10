package com.example.blogapp.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DI


class AuthorViewModelFactory(private val ctx: Context) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthorViewModel(ctx) as T
    }
}