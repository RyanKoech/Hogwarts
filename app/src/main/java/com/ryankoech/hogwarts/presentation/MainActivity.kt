package com.ryankoech.hogwarts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_character.presentation.CharacterScreen
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import com.ryankoech.hogwarts.feature_home.presentation.HomeScreen
import com.ryankoech.hogwarts.presentation.navigation.Screens
import com.ryankoech.hogwarts.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel : MainViewModel = hiltViewModel()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val navigateToCharacterScreen = { characterDtoItem : CharacterDtoItem ->
                viewModel.updateCharacter(characterDtoItem)
                navController.navigate(Screens.Character.route){
                    launchSingleTop = true
                }
            }


            HogwartsTheme {
                // A surface container using the 'background' color from the theme
                Scaffold { innerPadding ->

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colors.background
                    ) {

                        NavHost(navController = navController, startDestination = Screens.Home.route){

                            composable(Screens.Home.route) {

                                HomeScreen(
                                    navigateToCharacterScreen = navigateToCharacterScreen
                                )

                            }

                            composable(Screens.Character.route) {

                                CharacterScreen(
                                    character = viewModel.character!!
                                )

                            }


                        }

                    }
                }
            }
        }
    }
}