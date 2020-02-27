package oo

// private - same as in Java
// protected - same as in java
// internal - visible inside the same module
// public - same as in java
private val i = 42
fun a() = 17
private class Car (val brand: String, private val model: String){
    internal fun tellMeYourModel() = model
}


fun main() {
    val car = Car("BRAND", "MODEL")
    car.brand
    car.tellMeYourModel()
    println(i)
}