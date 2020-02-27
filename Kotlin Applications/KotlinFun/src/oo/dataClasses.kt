package oo

//
data class Address(val street: String, val number: Int, val postcode: String, val city: String)

fun main(args: Array<String>) {
    val residence = Address("Main Street", 42, "1234", "brisbane")
    val residence2 = Address("Main Street", 42, "1234","brisbane")
    println(residence)
    // Referential equality
    println(residence === residence2)
    // Structural equality, equals()
    println(residence == residence2)
    val neighbor = residence.copy(number = 43)
    println(neighbor)
    // Destructing operator
    println(residence.component1())
    val(street, number, postCode, city) = residence
    println("$street, $number, $postCode, $city")
}