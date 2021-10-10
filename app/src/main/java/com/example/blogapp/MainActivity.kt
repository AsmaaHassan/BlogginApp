package com.example.blogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blogapp.data.remote.RemoteDataSource
import com.example.blogapp.ui.theme.BlogAppTheme
import com.example.blogapp.ui.views.AuthorDataItem
import com.example.blogapp.ui.views.AuthorViewItem
import com.example.blogapp.ui.views.MainList
import com.example.blogapp.ui.views.details.AuthorDetails
import com.example.blogapp.viewmodels.AuthorViewModel
import com.example.blogapp.viewmodels.AuthorViewModelFactory
import org.kodein.di.DI
//import com.example.blogapp.viewmodels.WeatherViewModelFactory
//import okhttp3.internal.Internal.instance
//import org.kodein.di.android.closestKodein
//import org.kodein.di.android.
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainActivity() : ComponentActivity(), DIAware  {

override val di by lazy { (applicationContext as BlogginApp).di }

    private val authorViewModelFactory: AuthorViewModelFactory by instance()
    private val authorViewModel: AuthorViewModel by lazy {
        ViewModelProvider(
            this,
            authorViewModelFactory
        ).get(AuthorViewModel::class.java)
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            BlogAppTheme {
                NavHost(navController = navController, startDestination = "authorsList") {
                    // A surface container using the 'background' color from the theme
                    composable("authorsList") {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
//                            Greeting("Android")
                            MainList(navController = navController, authorViewModel = authorViewModel)

                        }
                    }
                    composable("authorDetails") {
                        AuthorDetails(authorViewModel = authorViewModel)
                    }

                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        BlogAppTheme {
            Greeting("Android")
        }
    }


}