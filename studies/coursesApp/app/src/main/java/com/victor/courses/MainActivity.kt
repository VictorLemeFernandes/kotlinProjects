package com.victor.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victor.courses.data.DataSource
import com.victor.courses.model.Topic
import com.victor.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoursesApp()
                }
            }
        }
    }
}

@Composable
fun CoursesApp() {
    val layoutDirection = LocalLayoutDirection.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            )
    ) {
        CoursesList(coursesList = DataSource.topics)
    }
}

@Composable
fun CoursesList(coursesList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
        items(coursesList) {course ->
            CourseCard(
                course = course,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun CourseCard(course: Topic, modifier: Modifier = Modifier) {
    Card (modifier = modifier
        .height(68.dp)
        .fillMaxWidth()) {
        Row (modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = course.imageCourseResourceId),
                contentDescription = stringResource(id = course.nameCourseResourceId),
                modifier = Modifier
                    .width(68.dp)
                    .fillMaxSize()
            )

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = stringResource(id = course.nameCourseResourceId))
                Text(text = course.codeCourseResourceId.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CoursesTheme {
        //CourseCard(course = Topic(R.string.architecture, 18, R.drawable.architecture))
        CoursesApp()
    }
}