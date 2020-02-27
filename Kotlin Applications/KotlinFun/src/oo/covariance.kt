package oo

open class Being
open class Person : Being()
class Student: Person()

fun main() {

    // Covariance = we can use a "more derived" type (a subtype)

    val people: MutableList<Person> = mutableListOf(Person(), Person())
    people.add(Student())// covariance

    val p: Person = Student()  // covariance
    // read only collections are covariant
    val students: List<Person> = listOf<Student>() // covariance




}