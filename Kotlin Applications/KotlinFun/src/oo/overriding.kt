package oo

abstract class Vehicle(open val brand: String = ""){
    open fun drive(){
        println("Driving")
    }
    abstract fun honk()
}
class Sedan(override val brand: String = "BRAND"):Vehicle(), Drivable{
    override val a: Int
        get() = 230

    override fun drive() {
        super<Drivable>.drive()
    }

    override fun honk() {
        println("Different honk")
    }
}

fun main() {
    val sedan = Sedan()
    sedan.drive()
    sedan.honk()
    sedan.a
}