package utils

import java.io.FileInputStream
import java.util.*

val fis = FileInputStream("server.properties")
val props = Properties()

fun loadProps() {
    props.load(fis)
}

fun getServerHost(): String {
    return getProp("server_host")
}

fun getServerPort(): Int {
    return Integer.parseInt(getProp("server_port"))
}

fun getMongoHost(): String {
    return getProp("mongo_host")
}

fun getMongoPort(): Int {
    return Integer.parseInt(getProp("mongo_port"))
}

@Suppress("UNCHECKED_CAST")
fun <T> getProp(key: String): T {
    return (props.getProperty(key) as T) ?: throw RuntimeException("could not find property $key")
}