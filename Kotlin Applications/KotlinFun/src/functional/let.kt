package functional

import java.io.File

fun main(args: Array<String>) {

    //let()
    //Scoping
    File("example.txt").bufferedReader().let{
        if(it.ready()){
            println(it.readLine() )
        }
    }
    // reader should not be visible
    //Working with nullables
    val str: String? = "Kotlin for android"
    str?.let {
        if (str.isNotEmpty()){
            str.toLowerCase()
        }
    }
}
