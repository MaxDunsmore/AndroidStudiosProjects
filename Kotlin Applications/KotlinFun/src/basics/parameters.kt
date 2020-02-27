package basics

fun main(args: Array<String>) {
    val together = concat(listOf("Kotlin", "Java", "Scala"), separator = " : ")
    val together2 = concat(separator = " : ", texts = listOf("Kotlin", "Java", "Scala"))
    println(together)
    println(together2)
}
fun concat(texts:List<String>, separator: String = ", ") = texts.joinToString(separator)
