import Viewmodel.BoredViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CollectionScreen(boredViewModel: BoredViewModel){
    Column(modifier = Modifier.fillMaxSize()){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.clickable(onClick = {
                boredViewModel.screen = 0
            }).padding(10.dp)
        )
        Card(modifier = Modifier.padding(20.dp).align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)){
            Text(text = "My Collection", style = MaterialTheme.typography.h5)
        }
        LazyColumn {
            items(boredViewModel.boredList){ bored ->
                Row(modifier = Modifier.padding(5.dp)){
                    Column(modifier = Modifier.padding(5.dp)) {
                        Text(text = bored.activity.toString(), style = MaterialTheme.typography.h6, color = MaterialTheme.colors.primary)
                        CardEvent(bored)
                    }
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.padding(5.dp).align(alignment = androidx.compose.ui.Alignment.CenterVertically).clickable(onClick = {
                            boredViewModel.deleteFromCollection(bored)
                        }))
                }
            }
        }
    }
}