package functional

fun main(args: Array<String>) {
    val inputRows = listOf(
        mapOf("input01.csv" to listOf(3, 5, -9922, 7, 11, 66)),
        mapOf("input02.csv" to listOf(1, 3, 4)),
        mapOf("input03.csv" to listOf()),
        mapOf("input04.csv" to listOf(45345, 4, 2, 15, 456))
    )

    val cleaned = inputRows
        .flatMap { it.values }
        .flatten()
        .filter { it in 0..100 }
        .toIntArray()
    println(cleaned.joinToString())
}