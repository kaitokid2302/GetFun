class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}

// where to put these? fun createDataStore(): DataStore<Preferences> in this file or create a new file?
// https://developer.android.com/topic/libraries/architecture/datastore