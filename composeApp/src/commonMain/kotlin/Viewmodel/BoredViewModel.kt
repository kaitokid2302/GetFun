package Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel


class BoredViewModel : ViewModel() {
    var count by mutableStateOf(0)
    fun increaseCount() {
        count += 19
    }
}