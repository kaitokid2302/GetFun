import Viewmodel.BoredViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier


@Composable
fun BeginOfScreen(){
    Card(modifier = androidx.compose.ui.Modifier.padding(2.dp)){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = androidx.compose.ui.Modifier.fillMaxSize()){
            Text(text = "Are you bored?", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h3)
            Text(text = "Let's find something to do!", fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun HomeScreen(boredViewModel: BoredViewModel) {
    MaterialTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
            BeginOfScreen()
            Spacer(modifier = androidx.compose.ui.Modifier.weight(0.5f))
            Text(modifier = androidx.compose.ui.Modifier.align(Alignment.Start), text = "Let's")

        }
    }
}