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
}