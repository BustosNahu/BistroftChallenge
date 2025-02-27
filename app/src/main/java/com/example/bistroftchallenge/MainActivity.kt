package com.example.bistroftchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.bistroftchallenge.core.lifecycle.AppLifecycleObserver
import com.example.bistroftchallenge.core.ui.theme.BistroftChallengeTheme
import com.example.bistroftchallenge.presentation.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var lifecycleObserver: AppLifecycleObserver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycle.addObserver(lifecycleObserver)

        setContent {
            BistroftChallengeTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}