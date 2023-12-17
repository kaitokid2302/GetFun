import Data.Bored
import Viewmodel.BoredViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Codepen
import compose.icons.feathericons.Star
import compose.icons.feathericons.User
import compose.icons.feathericons.Users
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
fun CardEvent(bored: Bored){
    Card(modifier = androidx.compose.ui.Modifier.padding(2.dp)){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = androidx.compose.ui.Modifier.fillMaxSize()){
            Row{
                Icon(
                    imageVector = FeatherIcons.Users,
                    contentDescription = "Person",
                )
                Column {
                    Text(text = "Participants: ")
                    Text(text = bored.participants.toString())
                }
            }
            Row{
                Icon(
                    imageVector = FeatherIcons.Codepen,
                    contentDescription = "Accessibility",
                )
                Column {
                    Text(text = "Accessibility: ")
                    Text(text = (bored.accessibility*100).toString() + "%")
                }
            }
            Row{
                Icon(
                    imageVector = FeatherIcons.Star,
                    contentDescription = "Star"
                )
                Column {
                    Text(text = "Type: ")
                    Text(text = bored.type)
                }
            }
        }
    }
}
@Composable
fun HomeScreen(boredViewModel: BoredViewModel) {
    var event by remember { mutableStateOf <Bored?> (null) }
    LaunchedEffect(boredViewModel){
        event = boredViewModel.getRandomEvent()
    }
    MaterialTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
            BeginOfScreen()
            Spacer(modifier = androidx.compose.ui.Modifier.weight(0.5f))
            Text(modifier = androidx.compose.ui.Modifier.align(Alignment.Start), text = "Let's")
            if(event != null){
                Text(event!!.activity, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h3)
                CardEvent(event!!)
            }
            Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
            Text("Not satisfied?")
            Button(onClick = {
                GlobalScope.launch {
                    event = boredViewModel.getRandomEvent()
                }
            }){
                Text("Try another one", color = Color.Blue)
            }
        }
    }
}