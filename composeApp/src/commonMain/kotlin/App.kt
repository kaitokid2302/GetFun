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
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


var boredViewModel = BoredViewModel()

@Composable
fun mainActivity() {
    LaunchedEffect(boredViewModel){
        boredViewModel.getAllEventByType()
    }
    if(boredViewModel.screen == 0){
        HomeScreen(boredViewModel)
    }
    else if(boredViewModel.screen == 1){
        BoredScreen(boredViewModel)
    }
}
