package oo

abstract class Shape(val name: String){
    abstract fun area(): Double
}
class Circle(name: String, private val radius: Double) : Shape(name){
    override fun area() = Math.PI*Math.pow(radius,2.0)

}

fun main(args: Array<String>) {
    val  shape = Circle("Large Circle", 17.6)
    println(shape.area())
}