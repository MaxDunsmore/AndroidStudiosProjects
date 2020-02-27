package oo

import java.util.*

val list: List<Int> = listOf(1,2,3)
class Timeline<E>{
    val date2Data: MutableMap<Date, E> = mutableMapOf()
    fun add(element: E){
        date2Data.put(Date(),element)
    }
    fun addAll(element: Collection<E>){
        element.forEach{add(it)}
    }

    fun getLatest(): E {
        return date2Data.values.last()
    }
}
fun <E>timeLineOf(vararg elements: E): Timeline<E>{
    val result = Timeline<E>()
    for (element in elements){
        result.add(element)
    }
    return result
}

fun main() {
    val timeline: Timeline<Int> = Timeline()
    timeline.add(2)
    timeline.getLatest()
    val timeLine2 = timeLineOf("","","")
}