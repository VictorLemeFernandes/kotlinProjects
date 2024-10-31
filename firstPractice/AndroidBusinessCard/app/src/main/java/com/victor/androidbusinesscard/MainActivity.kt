package com.victor.androidbusinesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.androidbusinesscard.ui.theme.AndroidBusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinnessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinnessCardApp(modifier: Modifier = Modifier) {
    Surface(color = Color(0xFFDCF2DE)) {
        Column(modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Column (modifier = modifier.weight(1f), verticalArrangement = Arrangement.Bottom) {
                MainContent(
                    name = "Victor Leme",
                    role = "Android Developer",
                )
            }
            Column (modifier = modifier.weight(1f), verticalArrangement = Arrangement.Bottom) {
                ContactContent(
                    phoneNumber = "(99)99999-9999",
                    socialMedia = "@VictorLeme",
                    email = "victor@mail.com"
                )
            }
        }
    }
}

@Composable
fun MainContent(name: String, role: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.android_logo)
    val textGreen: Long = 0xFF027333
    Column (
        modifier = modifier
            .fillMaxWidth(),

    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF073042))
                .fillMaxWidth(0.4f)
                .align(CenterHorizontally)
        ) {
            Image(painter = image, contentDescription = null)
        }
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 48.sp
        )

        Text(
            text = role,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color(textGreen),
            fontSize = 20.sp
        )
    }
}

@Composable
fun ContactContent(
    phoneNumber: String,
    socialMedia: String,
    email: String,
    modifier: Modifier = Modifier
) {
    val iconGreen: Long = 0xFF027333

    Column(modifier = modifier
        .padding(8.dp)) {
        Row(modifier = Modifier
            .padding(8.dp)) {
            Icon(
                Icons.Rounded.Phone,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp),
                tint = Color(iconGreen)
            )
            Text(text = phoneNumber)
        }
        Row(modifier = Modifier
            .padding(8.dp)) {
            Icon(
                Icons.Rounded.Share,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp),
                tint = Color(iconGreen)
            )
            Text(text = socialMedia)
        }
        Row(modifier = Modifier
            .padding(8.dp)) {
            Icon(
                Icons.Rounded.Email,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp),
                tint = Color(iconGreen)
            )
            Text(text = email)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidBusinessCardTheme {
        BusinnessCardApp()
    }
}