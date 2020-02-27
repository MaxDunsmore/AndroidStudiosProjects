package functional

fun modifyString(str: String, operation: (String) -> String): String {
    return str.toUpperCase()
}
fun main(args: Array<String>) {
    println(modifyString("Kotling is amazing") {it.toUpperCase()})

}