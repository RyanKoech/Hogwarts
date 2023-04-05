package com.ryankoech.hogwarts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ryankoech.hogwarts.R
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

            var topBarTitle by remember {
                mutableStateOf("")
            }

            HogwartsTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            backgroundColor = MaterialTheme.colors.surface,
                            contentColor = MaterialTheme.colors.onSurface,
                            elevation = 0.dp,
                            title = {
                                Text(
                                    text = topBarTitle,
                                    style = MaterialTheme.typography.h5
                                )
                            },
                            navigationIcon =
                            if ( currentDestination?.route != Screens.Home.route ) {
                                {
                                    IconButton(onClick = {
                                        navController.popBackStack()
                                    }) {
                                        Icon(
                                            painter = painterResource(R.drawable.icon_arrow_left),
                                            contentDescription = "Go back"
                                        )
                                    }
                                }
                            } else null,
                        )

                    }
                ) { innerPadding ->

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colors.background
                    ) {

                        NavHost(navController = navController, startDestination = Screens.Home.route){

                            composable(Screens.Home.route) {
                                topBarTitle = stringResource(Screens.Home.titleResId)
                                HomeScreen(
                                    navigateToCharacterScreen = navigateToCharacterScreen
                                )

                            }

                            composable(Screens.Character.route) {
                                topBarTitle = stringResource(Screens.Character.titleResId)
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