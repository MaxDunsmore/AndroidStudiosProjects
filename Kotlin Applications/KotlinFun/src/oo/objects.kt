package oo
import oo.House.Companion.getNormalHouse as createHouse
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

object CountryFactory{
    val a = 4
    fun createCountry() = Country("Australia")
}

object DefaultClickListener: MouseAdapter(){
    override fun mouseClicked(e: MouseEvent?) {
        super.mouseClicked(e)
        println("Mouse was clicked")
    }
}

fun main() {
    CountryFactory.a
    CountryFactory.createCountry()
    createHouse()
}