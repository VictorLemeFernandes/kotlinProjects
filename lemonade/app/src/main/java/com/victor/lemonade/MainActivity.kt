package com.victor.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade (modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentStep) {
                1 -> {
                    CardLemonade(
                        image = painterResource(id = R.drawable.lemon_tree),
                        text = stringResource(id = R.string.stringStep1),
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        },
                        modifier = Modifier
                    )
                }
                2 -> {
                    CardLemonade(
                        image = painterResource(id = R.drawable.lemon_squeeze),
                        text = stringResource(id = R.string.stringStep2),
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0){
                                currentStep = 3
                            }
                        },
                        modifier = Modifier
                    )
                }
                3 -> {
                    CardLemonade(
                        image = painterResource(id = R.drawable.lemon_drink),
                        text = stringResource(id = R.string.stringStep3),
                        onImageClick = { currentStep = 4 },
                        modifier = Modifier
                    )
                }
                4 -> {
                    CardLemonade(
                        image = painterResource(id = R.drawable.lemon_restart),
                        text = stringResource(id = R.string.stringStep4),
                        onImageClick = { currentStep = 1 },
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
@Composable
fun CardLemonade (image: Painter, text: String, onImageClick: () -> Unit,modifier: Modifier) {
    val backgroundGreen = Color(0xFFC9F2D7)
    val borderRadius = 25.dp

    Column (modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(borderRadius)) // P/ garantir que o container vai seguir a borda estabelecida
                .background(backgroundGreen), // Aparentemente tem que vir depois de estabelecer a borda...
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(borderRadius),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(painter = image, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        Lemonade(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}