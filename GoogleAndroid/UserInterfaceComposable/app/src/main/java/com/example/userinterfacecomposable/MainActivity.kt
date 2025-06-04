package com.example.userinterfacecomposable

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userinterfacecomposable.ui.theme.UserInterfaceComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserInterfaceComposableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InterfaceDisplay()
                }
            }
        }
    }
}

@Composable
fun InterfaceDisplay() {
    ProfileImage()
}

@Composable
fun ProfileImage(modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.baseline_android_24)
    val image2 = painterResource(R.drawable.phone_android_24dp)
    val image3 = painterResource(R.drawable.share_24dp)
    val image4 = painterResource(R.drawable.mail_24dp)

    Column (
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_color1))
    ){
        Column(
            horizontalAlignment =  Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(top = 180.dp)
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = modifier
                    .width(150.dp)
                    .background(colorResource(R.color.background_color2))
                    .height(120.dp)
            )
            Text(
                text = "android",
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .width(150.dp)
                    .height(50.dp)
                    .background(colorResource(R.color.background_color2))

            )
            Text(
                text = stringResource(R.string.full_name),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier =  modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = stringResource(R.string.title_name),
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
        }
        Column (
            horizontalAlignment =  Alignment.CenterHorizontally,
            verticalArrangement =  Arrangement.Bottom,
            modifier = modifier.padding(bottom = 50.dp)
        ){
            Row {
                Image(
                    painter = image2,
                    contentDescription = null,
                    modifier = modifier.height(30.dp)
                )
                Text(
                    text = stringResource(R.string.contact_number),
                    modifier = modifier.padding(start = 10.dp)
                )
            }
            Row {
                Image(
                    painter = image3,
                    contentDescription = null,
                    modifier = modifier.height(30.dp)
                )
                Text(
                    text = stringResource(R.string.contact_social),
                    modifier = modifier.padding(start = 10.dp)
                )
            }
            Row {
                Image(
                    painter = image4,
                    contentDescription = null,
                    modifier = modifier.height(30.dp)
                )
                Text(
                    text = stringResource(R.string.contact_email),
                    modifier = modifier.padding(start = 10.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UserInterfaceComposableTheme {
        InterfaceDisplay()
    }
}