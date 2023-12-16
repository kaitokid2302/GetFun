package Data

import kotlinx.serialization.Serializable
@Serializable
data class Bored(
    val accessibility: Double,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
)