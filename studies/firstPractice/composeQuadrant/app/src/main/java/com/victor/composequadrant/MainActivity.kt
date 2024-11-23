package com.victor.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victor.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrantScreen()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrantScreen() {
    ComposeQuadrant(
        firstTitle = stringResource(R.string.first_title),
        firstText = stringResource(R.string.first_text),
        secondTitle = stringResource(R.string.second_title),
        secondText = stringResource(R.string.second_text),
        thirdTitle = stringResource(R.string.third_title),
        thirdText = stringResource(R.string.third_text),
        fourthTitle = stringResource(R.string.fourth_title),
        fourthText = stringResource(R.string.fourth_text)
    )
}

@Composable
fun ComposeQuadrant(
    firstTitle: String, firstText: String, secondTitle: String, secondText: String,
    thirdTitle: String, thirdText: String, fourthTitle: String, fourthText: String, modifier: Modifier = Modifier
) {
    Column (modifier.fillMaxWidth()) {
        Row (modifier.weight(1f)) {
            ComposableCard(
                title = firstTitle,
                text = firstText,
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f))
            ComposableCard(
                title = secondTitle,
                text = secondText,
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f))
        }
        Row (modifier.weight(1f)) {
            ComposableCard(
                title = thirdTitle,
                text = thirdText,
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f))
            ComposableCard(
                title = fourthTitle,
                text = fourthText,
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ComposableCard(title: String, text: String, backgroundColor: Color, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeQuadrantPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantScreen()
    }
}