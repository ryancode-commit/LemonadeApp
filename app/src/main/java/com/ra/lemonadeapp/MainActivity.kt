package com.ra.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ra.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    TreeLemon(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
fun TreeLemon(modifier: Modifier = Modifier) {
    var current  by remember { mutableStateOf(1) }
    var squeeze by remember { mutableStateOf(0) }

    val imageResource = when(current) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val titleResource = when(current) {
        1 -> R.string.lemon_1
        2 -> R.string.lemon_2
        3 ->  R.string.lemon_3
        else ->  R.string.lemon_4
    }

    Column(
        modifier = modifier,
        horizontalAlignment =Alignment.CenterHorizontally
        ) {
        Text(text = stringResource(id =titleResource) + " \n${if(squeeze == 0) "" else (squeeze).toString() + " More" }")
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id =imageResource),
            contentDescription = null,
            modifier = Modifier.clickable {
                if (current == 1){
                    current = 2
                    squeeze = (2..4).random()
                }else if (current == 2 && squeeze == 0){
                    current = 3
                }else if (squeeze > 0) {
                    squeeze-=1
                    if (squeeze == 0){
                        current = 3
                    }
                }else if (current == 3){
                    current = 4
                }else{
                    current = 1
                }
            }
        )
    }
}