package com.tana.composedropupdownicon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.composedropupdownicon.ui.theme.ComposeDropupdownIconTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDropupdownIconTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { TopBar() }
                ) {
                    Surface(color = MaterialTheme.colors.background) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    MainCard()
}

@Composable
fun MainCard() {
    val (name, setText) = rememberSaveable { mutableStateOf("") }
    val (icon, setIcon) = remember { mutableStateOf(Icons.Default.ArrowDropDown) }
    val (isVisible, setVisibility) = remember{ mutableStateOf( false)}
    val handleIcon = {
        if (icon == Icons.Default.ArrowDropDown) {
            setIcon(Icons.Default.ArrowDropUp)
            setVisibility(true)
        } else {
            setIcon(Icons.Default.ArrowDropDown)
            setVisibility(false)
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(top = 50.dp)
    ) {
        Card(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            backgroundColor = Color(0xFF263238),
            contentColor = Color.White,

        ) {
            CardItemRow(text = name, icon = icon, onIconClicked = handleIcon)
        }
        if (isVisible) {
            NameInput(text = name, onTextChange = setText)
        }
    }
}

@Composable
fun CardItemRow(text: String, icon: ImageVector, onIconClicked: () -> Unit,  modifier: Modifier = Modifier) {
//    val iconUp = Icons.Default.ArrowDropUp
//    val iconDown = Icons.Default.ArrowDropDown
//    val (isVisible, setShowIcon) = remember{ mutableStateOf( false)}

    Row(
        modifier = Modifier.padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = modifier.weight(1f),
            style = TextStyle(
                fontSize = 20.sp
            )
        )
        Icon(
            imageVector = icon,
            contentDescription = "This is awesome",
            modifier = modifier
                .size(40.dp)
                .clickable { onIconClicked() }
        )
    }
}

@Composable
fun NameInput(text: String, onTextChange: (String) -> Unit) {
   Column() {
       OutlinedTextField(
           value = text,
           onValueChange = onTextChange,
           modifier = Modifier.fillMaxWidth(),
           label = { Text(text = "Enter name") }
       )
   }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "DropUpDown") },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeDropupdownIconTheme {
        App()
    }
}