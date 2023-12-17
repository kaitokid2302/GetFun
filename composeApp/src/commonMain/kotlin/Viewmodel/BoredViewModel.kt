package Viewmodel

import Data.Bored
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.storeOf
import io.github.xxfast.kstore.file.storeOf
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch
import okio.Path.Companion.toPath


class BoredViewModel : ViewModel() {
    var type = mutableListOf("education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork")
    // empty map<string, mutableList> called mapOfType
    var mapOfType = mutableMapOf<String, MutableList<Bored>>()
    var beginning = 1000000
    var n = 1000
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
    suspend fun getAllEventByType(){
        for(type in type){
            for(i in beginning..beginning+n){
                val bored = client.get("http://www.boredapi.com/api/activity?type=$type").body<Bored>()
                if(bored != null){
                    if(mapOfType.containsKey(type)){
                        mapOfType[type]?.add(bored)
                    }
                    else{
                        mapOfType[type] = mutableListOf(bored)
                    }
                }
            }
        }
    }
}