package basics

import java.io.IOException

fun main(args: Array<String>) {
    var input = try {
        getExternalInput()
    } catch (e: IOException) {
        e.printStackTrace()
        ""
    } finally {
        println("Finished trying to read external input")
    }
    println(input)
}

fun getExternalInput(): String {
    throw IOException("Could not read external input")
}
