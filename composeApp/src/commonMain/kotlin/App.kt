import Data.Bored
import Viewmodel.BoredViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
@Composable
fun mainActivity() {
    var boredViewModel = BoredViewModel()
    App(boredViewModel)
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(boredViewModel: BoredViewModel) {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            // new bored variable will be assing to the result of the function  suspend fun getRandomEvent():
            // Bored using remember and by mutableStateOf, but at the beginning it will be null
            var bored by remember { mutableStateOf<Bored?> (null) }

            LaunchedEffect(boredViewModel) {
                bored = boredViewModel.getRandomEvent()
            }
            Text(bored?.activity.toString())
            Text(bored?.link.toString())
            Text(getPlatform().name)
            Image(
                painter = painterResource("drawable/education.png"),
                contentDescription = "1"
            )
        }
    }
}