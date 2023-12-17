import Data.Bored
import Viewmodel.BoredViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun ListPage(boredViewModel: BoredViewModel, currentPage:Int, onClick : (String) -> Unit){
    Row(horizontalArrangement = Arrangement.SpaceEvenly){
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Back",
            modifier = Modifier.clickable(onClick = {onClick("back")}).padding(10.dp)
        )
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Black, shape = CircleShape)
        ) {
            Text(
                text = currentPage.toString(),
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Back",
            modifier = Modifier.clickable(onClick = {onClick("next")}).padding(10.dp)
        )
    }
}

@Composable
fun DisPlay(boredViewModel: BoredViewModel){
    // multiple page, each page has 10 event
    var showDialog by remember { mutableStateOf(false) }
    var currentEvent by remember { mutableStateOf<Bored?>(null) }
    var currentNumber by remember { mutableStateOf(1) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        ListPage(boredViewModel, currentNumber, onClick = {
            if(it == "back"){
                if(currentNumber > 1){
                    currentNumber -= 1
                }
            }
            else{
                currentNumber += 1
            }
        })
        LazyColumn(modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally)){
            var lower = (currentNumber-1)*10
            var upper = lower + 9
            var item = mutableListOf<Bored>()
            if(upper > boredViewModel.allEvenet.size){
                boredViewModel.getAllEventToLoad()
            }

            for (i in lower..upper) {
                item.add(boredViewModel.allEvenet[i])
            }

            items(item){
                SimpleCardEvent(it, boredViewModel, onClick = {
                    currentEvent = it
                    showDialog = true
                })
            }
        }
    }
    if(showDialog){
        FullCardEventDialog(currentEvent!!, boredViewModel, onClick = {
            showDialog = false
        })
    }
}

@Composable
fun SimpleCardEvent(bored: Bored, boredViewModel: BoredViewModel, onClick: () -> Unit){
    var showDialog by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth().padding(5.dp).height(40.dp).clickable(onClick = onClick)){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier.fillMaxSize()){
                Text(bored.activity!!, maxLines = 3)
                var cur = boredViewModel.boredList.find { it.key == bored.key }
                if(cur != null){
                    // icon star, yellow all inside and stroker outside
                    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.BottomEnd)) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FullCardEventDialog(bored: Bored, boredViewModel: BoredViewModel, onClick: () -> Unit){
    var showDialog by remember { mutableStateOf(true) }
    if(showDialog){
        Dialog(onDismissRequest = {
            showDialog = false
            onClick()
        }) {
            Card(modifier = Modifier.size(250.dp, 250.dp)){
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
                    CardEvent(bored)
                    Button(onClick = {
                        showDialog = false
                        onClick()
                    }) {
                        Text("OK")
                    }
                }
            }
        }
    }


}

@Composable
fun BoredScreen(boredViewModel: BoredViewModel){
    var currentText by remember { mutableStateOf("") }
    MaterialTheme {
        Card(modifier = Modifier.fillMaxSize()){
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable(onClick = {
                        boredViewModel.screen = 0
                    }).padding(10.dp).align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Spacer(modifier = Modifier.height(20.dp))
                DisPlay(boredViewModel)
            }
        }
    }
}