package oo

class Country(val name: String, val areaSqKm: Int){
    constructor(name: String) : this(name,0){
        println("Constructor called")
    }
    fun print() = "$name, $areaSqKm km^sq"
}

fun main(args: Array<String>) {
    val australia = Country("Australia",7_700_000)
    println(australia.print())
    val spain = Country("Spain")
    println(spain.print())
}