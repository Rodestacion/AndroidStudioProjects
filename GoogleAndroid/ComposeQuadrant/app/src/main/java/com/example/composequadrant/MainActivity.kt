package com.example.composequadrant

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
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

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
                    displayQuadrant()
                }
            }
        }
    }
}

@Composable
fun QuadrantBox(title: String,detail: String, backgroundColor: Color,modifier: Modifier = Modifier){

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.background(backgroundColor)
        ) {
            Text(
                text = "$title",
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(16.dp)
            )
            Text(
                text = "$detail",
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(16.dp)
            )
        }
}

@Composable
fun displayQuadrant(modifier: Modifier = Modifier) {
    Column (
        Modifier.fillMaxWidth()
    ){
        Row(Modifier.weight(1f)

        ) {
            QuadrantBox(
                stringResource(R.string.text_title),
                stringResource(R.string.text_detail),
                Color(0xFFEADDFF),
                modifier = modifier.weight(1f)
            )
            QuadrantBox(
                stringResource(R.string.image_title),
                stringResource(R.string.image_detail),
                Color(0xFFD0BCFF),
                modifier = modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)
        ) {
            QuadrantBox(
                stringResource(R.string.row_title),
                stringResource(R.string.row_detail),
                Color(0xFFB69DF8),
                modifier = modifier.weight(1f)
            )
            QuadrantBox(
                stringResource(R.string.column_title),
                stringResource(R.string.column_detail),
                Color(0xFFF6EDFF),
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuadrantPreview() {
    ComposeQuadrantTheme {
        displayQuadrant()
    }
}