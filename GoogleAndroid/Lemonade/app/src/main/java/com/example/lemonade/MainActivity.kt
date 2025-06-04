package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = Color.Yellow//MaterialTheme.colorScheme.background
                ) {
                    MakingLemonade()
                }
            }
        }
    }
}

@Composable
fun MakingLemonade(modifier:Modifier = Modifier) {
    var result by remember { mutableIntStateOf(1) }

    val imageResource = when(result) {
        1 -> R.drawable.lemon_tree
        5 -> R.drawable.lemon_drink
        6 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_squeeze
    }
    val textResource = when(result) {
        1 -> R.string.action_tree
        5 -> R.string.action_lemonade
        6 -> R.string.action_glass
        else -> R.string.action_lemon
    }
    val descriptionResource = when(result) {
        1 -> R.string.description_tree
        5 -> R.string.description_lemonade
        6 -> R.string.description_glass
        else -> R.string.description_lemon
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(imageResource),
            contentDescription = stringResource(descriptionResource),
            modifier = modifier
                .background(Color.Cyan)
                .clickable(
                onClick = {
                    if (result<6) {result++}
                    else {result =1}
                }
            )
        )
        Spacer(
            modifier = modifier
                .height(30.dp)
        )
        Text(
            text = stringResource(textResource),
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        MakingLemonade()
    }
}