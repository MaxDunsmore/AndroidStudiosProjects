package oo

interface  HouseFactory{
    fun createHouse(): House
}

class House(val numberOfRooms: Int, val price: Double){
    companion object: HouseFactory{
        const val HOUSES_FOR_SALE = 10
        fun getNormalHouse() = House(6,200000.0)
        private fun getLuxuryHouse() = House(42,7200000.0)
        override fun createHouse() = getLuxuryHouse()
    }
}

fun main() {
    val normalHouse = House.getNormalHouse()
    println(normalHouse.price)
    println(House.HOUSES_FOR_SALE)
}