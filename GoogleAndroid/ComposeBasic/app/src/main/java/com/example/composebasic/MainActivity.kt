package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeDetail()
                }
            }
        }
    }
}

@Composable
fun ComposeDetail() {
    ComposeArticle(
    stringResource(R.string.title),
    stringResource(R.string.details),
    stringResource(R.string.content)
    )
}

@Composable
fun ComposeArticle(title: String, details: String, content: String,modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.bg_compose_background)
    Column (){
        Image(
            painter = image,
            contentDescription = null,
            alignment = Alignment.TopCenter
        )
        Text(
            text = "$title",
            fontSize = 24.sp,
            modifier = modifier.padding(16.dp)

        )
        Text(
            text = "$details",
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text = "$content",
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = false)
@Composable
fun ComposePreview() {
    ComposeBasicTheme {
        ComposeDetail()
    }
}