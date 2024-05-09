package com.midterm.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.midterm.counterapp.ui.theme.CounterAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterAppTheme {
                val counter = remember {
                    mutableStateOf(0)
                }
                val isCounting = remember {
                    mutableStateOf(false)
                }
                
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                        Text(text = "${counter.value}", style = TextStyle(color = Color.Magenta,
                                                                    fontSize = 35.sp,
                                                                    fontWeight = FontWeight.ExtraBold))
                        Spacer(modifier = Modifier.height(130.dp))
                        CreateCircle(
                            counter = counter,
                            isCounting = isCounting,
                            scope = scope)
                        }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}

@Composable
fun CreateCircle(counter : MutableState<Int>, isCounting: MutableState<Boolean>, scope: CoroutineScope) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(105.dp)
            .clickable {
                isCounting.value = !isCounting.value
                if (isCounting.value) {
                    scope.launch {
                        while (isCounting.value) {
                            counter.value++
                            delay(1000)
                        }

                    }
                }
            },
        shape = CircleShape,
//        elevation = 4.sp
    )
            {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Tap")
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CounterAppTheme {
//        Greeting("Android")
//    }
//}