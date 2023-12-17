package Data

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
@Serializable
class Bored() : RealmObject {
    var accessibility: Double? = null
    @PrimaryKey
    var activity: String? = null
    var key: String? = null
    var link: String? = null
    var participants: Int? = null
    var price: Double? = null
    var type: String? = null
}