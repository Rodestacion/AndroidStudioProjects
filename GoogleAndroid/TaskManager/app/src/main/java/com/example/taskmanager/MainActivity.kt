package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskComplete()
                }
            }
        }
    }
}

@Composable
fun ImageComplete(allComplete: String, workGreet: String, modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.ic_task_completed)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center


    ){
        Image(painter = image,
            contentDescription = null,
            alignment = Alignment.Center
        )
        Text(
            text = allComplete,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 24.dp, bottom = 8.dp)

        )
        Text(
            text = workGreet,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}
@Composable
fun TaskComplete() {
    ImageComplete(
        stringResource(R.string.all_complete),
        stringResource(R.string.work_greet)
    )
}

@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    TaskManagerTheme {
        TaskComplete()
    }
}