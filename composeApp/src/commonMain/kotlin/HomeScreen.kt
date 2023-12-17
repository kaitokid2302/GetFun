import Data.Bored
import Viewmodel.BoredViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text(text = "Are you bored?", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h5)
        Text(text = "Let's find something to do!", fontWeight = FontWeight.Bold)
    }
}


@Composable
fun CardEvent(bored: Bored){
    Card(modifier = androidx.compose.ui.Modifier.padding(2.dp).height(200.dp)){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = androidx.compose.ui.Modifier.fillMaxSize(0.9f)){
            Row(modifier = androidx.compose.ui.Modifier.padding(5.dp).fillMaxWidth(0.7f)){
                Icon(
                    imageVector = FeatherIcons.Users,
                    contentDescription = "Person",
                )
                Column(modifier = androidx.compose.ui.Modifier.padding(5.dp)) {
                    Text(text = "Participants: ")
                    Text(text = bored.participants.toString())
                }
            }
            Row(modifier = androidx.compose.ui.Modifier.padding(5.dp).fillMaxWidth(0.7f)){
                Icon(
                    imageVector = FeatherIcons.Codepen,
                    contentDescription = "Accessibility",
                )
                Column {
                    Text(text = "Accessibility: ")
                    Text(text = (bored.accessibility!!*100).toString() + "%")
                }
            }
            Row(modifier = androidx.compose.ui.Modifier.padding(5.dp).fillMaxWidth(0.7f)){
                Icon(
                    imageVector = FeatherIcons.Star,
                    contentDescription = "Star",
                )
                Column {
                    Text(text = "Type: ")
                    Text(text = bored.type!!)
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
        Card(modifier = androidx.compose.ui.Modifier.fillMaxSize()){
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = androidx.compose.ui.Modifier.fillMaxSize()){
                Spacer(modifier = androidx.compose.ui.Modifier.weight(0.8f))
                BeginOfScreen()
                Text(modifier = androidx.compose.ui.Modifier.align(Alignment.Start).padding(5.dp), text = "Let's")
                Spacer(modifier = androidx.compose.ui.Modifier.weight(0.5f))
                if(event != null){
                    Text(event!!.activity!!)
                    Spacer(modifier = androidx.compose.ui.Modifier.weight(0.5f))
                    CardEvent(event!!)
                }
                Spacer(modifier = androidx.compose.ui.Modifier.weight(0.8f))
                Text("Not satisfied?")
                Text("Try another one", color = Color.Blue, modifier = androidx.compose.ui.Modifier.clickable {
                    GlobalScope.launch {
                        event = boredViewModel.getRandomEvent()
                    }
                })
                Button(onClick = {
                    boredViewModel.addToCollection(event!!)
                })
                {
                    Text("Add to collection")
                }
                Text("Discover More?", color = Color.Blue, modifier = androidx.compose.ui.Modifier.clickable {
                    boredViewModel.screen = 1
                })
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "to my collection",
                    modifier = androidx.compose.ui.Modifier.padding(10.dp).align(Alignment.End).clickable{
                        boredViewModel.screen = 2
                    }
                )
            }
        }
    }
}