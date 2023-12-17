package Viewmodel

import Data.Bored
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Path
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.storeOf
import io.github.xxfast.kstore.file.storeOf
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.Path.Companion.toPath
import java.nio.file.Paths


class BoredViewModel : ViewModel() {
    var boredList = mutableStateListOf<Bored>()
    var boredToDisPlayList = mutableStateListOf<Bored>()
    var currentText by mutableStateOf("")

    fun addToCollection(bored: Bored){

        // write to file mydata.txt
        var path = Paths.get("mydata.txt")
        path.toFile().appendText(bored.key + "\n")
        boredList.add(bored)
    }


    fun deleteFromCollection(bored: Bored){
        // delete from file mydata.txt
        var path = Paths.get("mydata.txt")
        var list = path.toFile().readLines()
        path.toFile().writeText("")
        for (key in list){
            if(key != bored.key){
                path.toFile().appendText(key + "\n")
            }
        }
        var newList = boredList.toMutableList()
        boredList.clear()
        for (b in newList){
            if(b.key != bored.key){
                boredList.add(b)
            }
        }
    }

    suspend fun getEventByKey(key: Int): Bored?{
        var _key = key.toString()
        var bored = client.get("http://www.boredapi.com/api/activity?key=$_key").body<Bored>()
        return bored
    }

    suspend fun getAllCollection(){
        boredList.clear()
        var path = Paths.get("mydata.txt")
        var list = path.toFile().readLines()
        for (key in list){
            var bored = getEventByKey(key.toInt())
            if(bored != null){
                boredList.add(bored)
            }
        }
    }


    var type = mutableListOf("education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork")
    // empty map<string, mutableList> called mapOfType
    var beginning = 1000000
    var n = 10000
    var allEvenet = mutableListOf<Bored>()
    var sizeOfAllEvent by mutableStateOf(0)
    var screen by mutableStateOf(0)
    var client = HttpClient(){
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun getRandomEvent(): Bored{
        val bored = client.get("http://www.boredapi.com/api/activity/").body<Bored>()
        return bored
    }
    fun getAllEventToLoad() {
        viewModelScope.launch {
            var path = Paths.get("currentnumber.txt")
            var cur = path.toFile().readText().toInt()
            for(i in cur + 1..cur+n){
                var bored = client.get("http://www.boredapi.com/api/activity/?type=${type[i%type.size]}").body<Bored>()
                allEvenet.add(bored)
                sizeOfAllEvent++
            }
            // clear all content of file and rewrite cur + n + 1
            path.toFile().writeText("")
            path.toFile().appendText((cur + n + 1).toString())
        }

    }
}