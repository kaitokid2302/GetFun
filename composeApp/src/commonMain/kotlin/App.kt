import Viewmodel.BoredViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Button(onClick = { boredViewModel.increaseCount() }) {
                Text("Click me")
            }
            Text(text = "You have clicked the button ${boredViewModel.count} times")
        }
    }
}