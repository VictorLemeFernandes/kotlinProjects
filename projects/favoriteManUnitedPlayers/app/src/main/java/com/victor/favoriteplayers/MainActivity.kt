package com.victor.favoriteplayers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.favoriteplayers.ui.theme.FavoritePlayersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavoritePlayersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    var player by remember { mutableStateOf(0) }
    val playerPhotoResource = when (player) {
        0 -> R.drawable.bruno
        1 -> R.drawable.hojlund
        2 -> R.drawable.lisandro
        3 -> R.drawable.mainoo
        else -> R.drawable.mazraoui
    }
    val playerInformation = listOf(
        mapOf("name" to "Bruno Fernandes", "apperances" to "255", "age" to "30", "goals" to "84"),
        mapOf("name" to "Rasmus Hojlund", "apperances" to "59", "age" to "21", "goals" to "21"),
        mapOf("name" to "Lisandro Martinez", "apperances" to "78", "age" to "26", "goals" to "1"),
        mapOf("name" to "Kobie Mainoo", "apperances" to "46", "age" to "19", "goals" to "5"),
        mapOf("name" to "Mazraoui", "apperances" to "22", "age" to "27", "goals" to "0")
    )

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.weight(0.5f))
        Box(
            modifier = Modifier
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = playerPhotoResource),
                contentDescription = null,
                modifier = Modifier
                    .align(Center)
                    .padding(8.dp)
            )
        }
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors( containerColor = Color.Gray )
        ) {
            Column (
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = playerInformation[player]["name"]!!,
                    modifier = Modifier.align(CenterHorizontally),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Jogos: ${playerInformation[player]["apperances"]}")
                    Text(text = "Idade: ${playerInformation[player]["age"]}")
                    Text(text = "Gols: ${playerInformation[player]["goals"]}")
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.Bottom) {
            Button(
                onClick = {
                    if (player > 0) {
                        player--
                    }
                }
            ) {
                Text(text = stringResource(R.string.previous_text_button))
            }

            Button(
                onClick = {
                    if (player < 4) {
                        player++
                    }
                }
            ) {
                Text(text = stringResource(R.string.next_text_button))
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    FavoritePlayersTheme {
        App()
    }
}