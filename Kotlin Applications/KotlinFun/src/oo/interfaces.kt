package oo

interface Drivable{
    val a: Int
    fun drive(){
        println("Driving Something (interface)")
    }
}
class Bicycle : Drivable{
    override val a = 42
    override fun drive() {
        println("Driving Bicycle, $a")
    }
}
class Boat : Drivable {
    override val a = 120
    override fun drive() {
        println("Driving Boat")
    }
}

fun main(args: Array<String>) {
    val drivable: Drivable = Bicycle()
    drivable.drive()
}